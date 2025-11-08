package com.tvo.technologies.saferecruitment.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GeminiAiValidationService implements AiValidationService {

    private final Client client;

    @Override
    public ValidationResponse validate(VacancyValidationRequest vacancy) {
        String contextPrompt = """
                  You are an expert Vacancy Validation System. Your task is to analyze a job vacancy, determine its validity, assess the risk of it being a scam, fraud, or human trafficking attempt, and provide a **confidence score** for your verdict. Base your analysis strictly on **internal consistency and known industry red flags** within the provided data fields.
                  
                  **The Vacancy Data Structure (VacancyValidationRequest):**
                  - **String position:** The job title.
                  - **String description:** The detailed job description.
                  - **List<String> requiredSkills:** A list of skills needed for the role.
                  - **BigDecimal salary:** The offered salary (e.g., in USD/year or local currency).
                  - **String location:** The geographical location of the job.
                  - **Boolean interviewProcessDetails:** True if a standard interview process (multiple stages, background check, etc.) is mentioned, False otherwise.
                  
                  **Your Analysis Steps:**
                  1.  **Truthfulness/Legitimacy Check:**
                      * Assess the **realism** of the `salary` relative to the `position` and `requiredSkills` (e.g., extremely high salary for unskilled labor).
                      * Check the `description` for **vague language**, **poor grammar**, or **over-the-top urgency** often used in scams.
                      * Check for a **mismatch** between the complexity of `requiredSkills` and the `position` or `salary`.
                  2.  **Scam/Fraud/Trafficking Risk Assessment:**
                      * Flag if the `description` or `position` mentions terms suggesting **unusual financial requests** (e.g., 'training kit fee', 'process payment', 'investment required', 'initial fee').
                      * Flag if the `salary` is disproportionately high for a simple role, suggesting a money mule or reshipping scam.
                      * Flag if `interviewProcessDetails` is **False**, which often indicates a rushed, non-legitimate hiring process, especially for high-paying or sensitive roles.
                      * Flag the `location` if it suggests high-risk remote international work without clear company details mentioned.
                  3.  **Determine ValidationVerdict, RiskCategory, and Confidence:**
                      * **ValidationVerdict:**
                          * **SCAM:** If the job is internally inconsistent, contains explicit fee/payment requests, or has multiple high-risk red flags.
                          * **TRUTHFULNESS:** If the vacancy details are internally consistent and no major red flags are detected.
                      * **RiskCategory:**
                          * **HIGH:** Multiple critical red flags, strong internal inconsistency, or high-risk language.
                          * **MEDIUM:** Some minor flags (e.g., generic description, salary slightly high, location vague).
                          * **LOW:** No red flags found and the data is consistent.
                      * **Confidence:** A float value from **0.0 to 1.0** representing the certainty of the `verdict`. Assign a high confidence (e.g., >0.9) when multiple critical red flags are present or when the description is perfectly normal. Assign medium confidence (e.g., 0.5-0.8) when the evidence is mixed or ambiguous.
                  
                  **Create VacancyRedFlags Enum Values:**
                  * `UNREALISTIC_SALARY`
                  * `VAGUE_JOB_DESCRIPTION`
                  * `RUSHED_PROCESS_NO_INTERVIEW`
                  * `MISMATCHED_SKILLS_AND_PAY`
                  * `LANGUAGE_INDICATING_FEES`
                  * `HIGH_RISK_REMOTE_LOCATION`
                  * `NO_RED_FLAGS`
                  
                  **Final Output Entity Structure (ValidationResponse):**
                  ```json
                  {
                    "verdict": "SCAM" | "TRUTHFULNESS",
                    "description": "A concise summary (1-2 sentences) of the validation result, explaining the main reasons for the verdict and risk assessment.",
                    "riskCategory": "LOW" | "MEDIUM" | "HIGH",
                    "confidence": 0.0, // float value from 0.0 to 1.0
                    "redFlags": [
                      // List of relevant enum values from the list above.
                      // Return an empty array if no red flags are detected.
                    ]
                  }. Do not send any response now, just understand the context.
                """;

        String vacancyValidationPrompt = """
                    Now your task is to analyze the vacancy by criteria described before.
                    
                    Position: %s,
                    Description: %s,
                    requiredSkills: %s,
                    salary: %s,
                    location: %s,
                    interviewProcessDetails: %s.
                    
                    As a response return JUST JSON entity.
                """.formatted(
                vacancy.position(),
                vacancy.description(),
                vacancy.requiredSkills(),
                vacancy.salary(),
                vacancy.location(),
                vacancy.interviewProcessDetails()
        );

        GenerateContentResponse response = client
                .models
                .generateContent(
                        "gemini-2.5-flash",
                        contextPrompt + "\n" + vacancyValidationPrompt,
                        null);

        log.info("Returning a response from Gemini: {}", response.text());
        return null;
    }

    @Override
    public ValidationResponse validate(CompanyValidationRequest company) {
        GenerateContentResponse response = client
                .models
                .generateContent(
                        "gemini-2.5-flash",
                        "Hello, Gemini! How are you?",
                        null);

        log.info("Returning a response from Gemini: {}", response.text());
        return null;
    }
}
