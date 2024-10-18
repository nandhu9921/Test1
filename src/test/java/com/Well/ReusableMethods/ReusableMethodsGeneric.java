package com.Well.ReusableMethods;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import java.util.List;

import org.openqa.selenium.WebElement;

public class ReusableMethodsGeneric extends BaseClass {
	String PortfolioAndRatingLocAccDocumentTable = "PortfolioAndRatingLocAccDocumentTable";
	String PortfolioScoreCardVerificationAssignLocSavebtn = "PortfolioScoreCardVerificationAssignLocSavebtn";

	public void uploadModalFirstScreenGeneric(String SheetName, int rowNum, String Commodity, String FileName,
			Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,
			Boolean IntentCheckboxRequired) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardFeatureLoader");
		rc.betaFeature();
		switch (Commodity) {
		case "Ratings":
			if (VerificationMethodValidationRequired) {
				String VerificationMethodExpected = data.getCellData(SheetName, "VerificationMethod", rowNum);
				String FeatureVerificationNameV2 = "Performance Test OR Sensor Data";
				String VerificationMethodActual = CommonMethod.getText("PortfolioScoreVerifyUploadVerificationMethod");
				testlog.info("VerificationMethod: " + VerificationMethodActual);
				if (TestNGTestName.contains("V2-Pilot")) {
					/** Valid VerificationMethod */
					CommonMethod.negativesoftassertFieldValid(VerificationMethodActual, VerificationMethodExpected,
							"Verification Method doesn't match");
				} else {
					CommonMethod.negativesoftassertFieldValid(VerificationMethodActual, FeatureVerificationNameV2,
							"Verification Method doesn't match");
				}
				testlog.info("Then User verifies verification Method");
			}

			if (PartNameRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				testlog.info("And User clicks on Add feature");
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
						"PortfolioScoreCardVerificationUploadAddfeature");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadFeatureRemove", 0);
				testlog.info("And User verifies Removelink should be visible");
			}

			// CommonPieceforuploadfile
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardFeatureLoader");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
			CommonMethod.uploadFile("DocumentsUpload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");
			testlog.info("And User clicks on Upload Button");

			if (NoteRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote",
						data.getCellData(SheetName, "NoteComment", rowNum));
				testlog.info("And User enter data to AddNote field");
			}

			if (IntentCheckboxRequired) {
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioScorecardVerifyTaskUploadIntentStage");
				CommonMethod.negativeAssertElementNotPresentFalse("PortfolioScorecardVerifyTaskUploadIntentStage","Intent checkbox is present");
			}
			rc.ScorecardUploadSaveButton();
			break;

		case "Portfolio":
			if (VerificationMethodValidationRequired) {
				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_03_01_FeatureScorecardUpload")) {
					String FeatureVerificationNameV2 = "Performance Test OR Sensor Data";
					String VerificationMethod = CommonMethod.getText("PortfolioScoreVerifyUploadVerificationMethod");
					testlog.info("VerificationMethod: " + VerificationMethod);
					if (TestNGTestName.contains("V2-Pilot")) {
						/** Valid VerificationMethod */
						CommonMethod.negativesoftassertFieldValid(
								data.getCellData(SheetName, "VerificationMethod", rowNum), VerificationMethod,
								"Verification Method doesn't match");
					} else {
						CommonMethod.negativesoftassertFieldValid(FeatureVerificationNameV2, VerificationMethod,
								"Verification Method doesn't match");
					}
					testlog.info("Then User verifies verification Method");
					/** Valid DocumentType */
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardDocumentType"),
							data.getCellData(SheetName, "DocumentType", rowNum), "Document Type doesn't match");
					testlog.info("DocumentType: " + CommonMethod.getText("PortfolioScorecardDocumentType"));
					testlog.info("And User verifies DocumentType");
					/** Valid FeatureName */
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadFeatureName", 60);
					CommonMethod.negativesoftassertFieldValid(
							CommonMethod.getText("PortfolioScorecardUploadFeatureName"),
							data.getCellData(SheetName, "FeatureName", rowNum), "Feature Name doesn't match");
					testlog.info("FeatureName: " + CommonMethod.getText("PortfolioScorecardUploadFeatureName"));
					testlog.info("And User verifies PartName");
					/** Valid PartName */
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadPartName", 60);
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardUploadPartName"),
							data.getCellData(SheetName, "PartName", rowNum), "PartName doesn't match");
					testlog.info("PartName: " + CommonMethod.getText("PortfolioScorecardUploadPartName"));
					testlog.info("And User verifies PartNo.");
					/** Valid SpaceType */
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadSpaceType", 60);
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardUploadSpaceType"),
							data.getCellData(SheetName, "SpaceType", rowNum), "SpaceType doesn't match");
					testlog.info("SpaceType: " + CommonMethod.getText("PortfolioScorecardUploadSpaceType"));
					testlog.info("And User verifies SpaceType");
					if (TestNGTestName.contains("V2-Pilot")) {
						/** Valid V2 OptionName */
						CommonMethod.WaitUntilPresence("PortfolioScorecardUploadOptionName", 60);
						CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardOptionv2p"),
								"Option 1:", "OptionName doesn't match");
						testlog.info("OptionName: " + CommonMethod.getText("PortfolioScorecardUploadOptionName"));
						testlog.info("And User verifies OptionName");
					} else {
						/** Valid V2 OptionName */
						CommonMethod.WaitUntilPresence("PortfolioScorecardUploadOptionName", 60);
						CommonMethod.negativesoftassertFieldValid(
								CommonMethod.getText("PortfolioScorecardUploadOptionName"),
								data.getCellData(SheetName, "OptionName", rowNum), "OptionName doesn't match");
						testlog.info("OptionName: " + CommonMethod.getText("PortfolioScorecardUploadOptionName"));
						testlog.info("And User verifies OptionName");
					}
					CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");					
					CommonMethod.WaitUntilPresence("PortfolioScoreCardVerificationUploadAddfeature", 60);
					testlog.info("And User verifies Option to Add multiple parts button");
					CommonMethod.WaitUntilPresence("PortfolioScoreCardVerificationUploadAddfeature", 60);
					CommonMethod.assertisElementPresentTrue("PortfolioScoreCardVerificationUploadAddfeature",
							"Add feature parts is not visible");
					testlog.info("And User verifies Add feature parts option");
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
							"PortfolioScoreCardVerificationSelectFeature");
					testlog.info("And User clicks on Add feature");
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature",
							data.getCellData(SheetName, "AddPartOption", rowNum));
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
							"PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
							"PortfolioScoreVerifyUploadVerificationMethod");
					/** Valid Added part remove link */
					CommonMethod.scrolldowntoElement("PortfolioScoreVerifyUploadVerificationMethod");
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadRemovelink", 60);
					CommonMethod.Robustclick("PortfolioScorecardUploadRemovelink");
					CommonMethod
							.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioScorecardUploadRemovelink");
					testlog.info("And User clicks on Add feature Removelink");
					CommonMethod.assertisNotElementPresent("PortfolioScorecardUploadRemovelink",
							"Removelink is visible");
					CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.uploadFile("DocumentsUpload", FeaturefileUpload,
							"PortfolioScorecardUploadRemoveLink");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndContinuebtn", 0);
					CommonMethod.assertisElementPresentTrue("SaveAndContinuebtn", "Upload button is not visible");
					testlog.info("And User verifies Enable Upload button");
					CommonMethod.Robustclick("PortfolioScorecardUploadRemoveLink");
					testlog.info("And User clicks on Upload Remove icon");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndContinueDisablebtn", 0);
					CommonMethod.assertisElementPresentTrue("SaveAndContinueDisablebtn",
							"Upload button disable is not visible");
					testlog.info("And User verifies Upload button disable");
				}

				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_01_02_MeetThresholdsforOrganicGasesFeature")) {
					/*
					 * Valid FeatureName, DocumentType and VerificationMethod
					 */
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadFeatureName", 0);
					CommonMethod.negativesoftassertFieldValid(
							CommonMethod.getText("PortfolioScorecardUploadFeatureName"),
							"Meet Thresholds for Organic Gases", "Feature Name doesn't match");
					testlog.info("FeatureName: " + CommonMethod.getText("PortfolioScorecardUploadFeatureName"));
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardDocumentType"),
							"Feature verification", "Document Type doesn't match");
					testlog.info("DocumentType: " + CommonMethod.getText("PortfolioScorecardDocumentType"));
					String VerificationMethod = CommonMethod.getText("PortfolioScoreVerifyUploadVerificationMethod");
					testlog.info("VerificationMethod: " + VerificationMethod);
					/*
					 * Adding part A05.2
					 */
					if (VerificationMethod.equalsIgnoreCase("Performance Test")) {
						CommonMethod.negativesoftassertFieldValid(VerificationMethod, "Performance Test",
								"Verification Method doesn't match");
						testlog.info("**Verifies Verification Method successful**");
						CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
								"PortfolioScoreCardVerificationUploadAddfeature", 0);
						CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
								"PortfolioScoreCardVerificationSelectFeature");
						CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", "A05.2");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
								"PortfolioScoreCardVerificationAddPart", 0);
						CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
								"PortfolioScoreCardVerificationSelectFeature");
						CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadAddfeature",
								"PortfolioScoreCardVerificationSelectFeature");
						/*
						 * Valid Added part remove link
						 */
						CommonMethod.scrolldowntoElement("PortfolioScoreVerifyUploadVerificationMethod");
						CommonMethod.WaitUntilVisibility("PortfolioScorecardUploadRemovelink", 60);
						testlog.info("**Verifies Remove link in Upload Feature successful**");
					}
				}

				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_03_06_AuditScorecardUpload")) {
					/** Valid VerificationMethod */
					CommonMethod.negativesoftassertFieldValid(
							CommonMethod.getText("PortfolioScoreVerifyUploadVerificationMethod"),
							data.getCellData(SheetName, "AuditVerificationMethod", rowNum),
							"Verification Method doesn't match");
					testlog.info("Then User verifies verification Method");
					/** Valid DocumentType */
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardDocumentType"),
							data.getCellData(SheetName, "AuditDocumentType", rowNum), "Document Type doesn't match");
					testlog.info("DocumentType: " + CommonMethod.getText("PortfolioScorecardDocumentType"));
					testlog.info("And User verifies DocumentType");
					/** Valid FeatureName */
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadFeatureName", 60);
					CommonMethod.negativesoftassertFieldValid(
							CommonMethod.getText("PortfolioScorecardUploadFeatureName"),
							data.getCellData(SheetName, "AuditName", rowNum), "Audit Name doesn't match");
					testlog.info("FeatureName: " + CommonMethod.getText("PortfolioScorecardUploadFeatureName"));
					testlog.info("And User verifies FeatureName");
					/** Valid PartName */
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadPartName", 60);
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardUploadPartName"),
							data.getCellData(SheetName, "AuditPartName", rowNum), "PartName doesn't match");
					testlog.info("PartName: " + CommonMethod.getText("PortfolioScorecardUploadPartName"));
					testlog.info("And User verifies PartName");
					/** Valid SpaceType */
					CommonMethod.WaitUntilPresence("PortfolioScorecardUploadSpaceType", 60);
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardUploadSpaceType"),
							data.getCellData(SheetName, "AuditSpaceType", rowNum), "SpaceType doesn't match");
					testlog.info("SpaceType: " + CommonMethod.getText("PortfolioScorecardUploadSpaceType"));
					testlog.info("And User verifies SpaceType");
					if (TestNGTestName.contains("V2-Pilot")) {
						/** Valid V2 OptionName */
						CommonMethod.WaitUntilPresence("PortfolioScorecardUploadOptionName", 60);
						CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardOptionv2p"),
								"Option 1:", "OptionName doesn't match");
						testlog.info("OptionName: " + CommonMethod.getText("PortfolioScorecardUploadOptionName"));
						testlog.info("And User verifies OptionName");
					} else {
						/** Valid V2 OptionName */
						CommonMethod.WaitUntilPresence("PortfolioScorecardUploadOptionName", 60);
						CommonMethod.negativesoftassertFieldValid(
								CommonMethod.getText("PortfolioScorecardUploadOptionName"),
								data.getCellData(SheetName, "AuditOptionName", rowNum), "OptionName doesn't match");
						testlog.info("OptionName: " + CommonMethod.getText("PortfolioScorecardUploadOptionName"));
						testlog.info("And User verifies OptionName");
					}
					CommonMethod.assertisNotElementPresent("PortfolioScoreCardVerificationUploadAddfeature",
							"Addfeature element is present");
					testlog.info("And User verifies Add feature shouldn't Present");
					CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
				}

				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_03_07_AlternativeScorecardUpload")) {
					CommonMethod.WaitUntilPresence("PortfolioScoreCardVerificationUploadAddfeature", 180);
					CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
							"PortfolioScoreCardVerificationSelectFeature");
					testlog.info("And User clicks on Add feature");
					CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature",
							data.getCellData(SheetName, "AlternativeFeaturePartName", rowNum));
					int OptionSize = CommonMethod.getdropdownSize("PortfolioScorecardOptionInAddFeature");
					CommonMethod.negativesoftassertFieldValid(String.valueOf(OptionSize), "5",
							"Option count in Muliple Parts doesn't match");
					testlog.info("Then User verifies Option count in Add Feature");
					CommonMethod.selectdropdownrandom("PortfolioScorecardOptionInAddFeature");
					testlog.info("And User select Option");
					CommonMethod.assertisNotElementPresent("PortfolioScorecardSpaceInAddFeature",
							"Space type field shouldn't Present");
					testlog.info("And User verifies Space type field shouldn't visible");
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
							"PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
							"PortfolioScoreCardVerificationAddNote");
					CommonMethod.assertisNotElementPresent("PortfolioScorecardUploadFeatureRemove",
							"Add Part link shouldn't Present");
					testlog.info("And User verifies Added feature link");
				}
			}

			if (PartNameRequired) {
				String partId = null;
				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_01_01_MeetThresholdsforParticulateMatter")) {
					partId = "A01.3";
				}
				CommonMethod.WaitUntilPresence("PortfolioScoreCardVerificationUploadAddfeature", 60);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				testlog.info("And User clicks on Add feature");
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", partId);
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				}
				CommonMethod.WaitUntilPresence("PortfolioScoreCardVerificationAddPart", 60);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
						"PortfolioScoreCardVerificationUploadAddfeature");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
				testlog.info("And User verifies Removelink should be visible");
			}
			// CommonPieceforuploadfile
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
			CommonMethod.uploadFile("DocumentsUpload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");

			if (NoteRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote",
						data.getCellData(SheetName, "NoteComment", rowNum));
				testlog.info("And User enter data to AddNote field");
			}

			if (IntentCheckboxRequired) {
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioScorecardVerifyTaskUploadIntentStage");
				CommonMethod.negativeAssertElementNotPresentFalse("PortfolioScorecardVerifyTaskUploadIntentStage","Intent checkbox is present");
			}

			rc.ScorecardUploadSaveButton();
			break;

		case "SingleAsset":

			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardDocbtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectscorecardDocbtn",
					"V2Projectscorecardverificationdropdown");
			testlog.info("And User clicks on Document Plus icon");
			testlog.info("TaskName : Performance Test OR Sensor Data");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecardverificationdropdown", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocupload", 0);
			CommonMethod.uploadFile("V2Projectscorecarddocupload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User upload scorecard Document");

			if (FileName.contains("Audit")) {
				if (TestNGTestName.contains("V2-Pilot")) {
					CommonMethod.selectdropdownVisibletext("V2Projectscorecardverificationdropdown",
							"On-site Photographs");
				} else {
					CommonMethod.selectdropdownVisibletext("V2Projectscorecardverificationdropdown",
							"Technical Document (Audited)");
				}

			} else {
				CommonMethod.selectdropdownrandom("V2Projectscorecardverificationdropdown");
			}
			
			if (IntentCheckboxRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScorecardVerifyTaskUploadIntentStage", 1);
				CommonMethod.assertisElementPresentTrue("PortfolioScorecardVerifyTaskUploadIntentStage","Intent checkbox is not present");
				CommonMethod.ClickCheckbox("PortfolioScorecardVerifyTaskUploadIntentStage");
			}
			
			testlog.info("And User select the Verification");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocuploadsubmit", 0);
			CommonMethod.RobustclickElementVisible("V2Projectscorecarddocuploadsubmit",
					"V2ProjectscorecardVerifyUploadDoc");
			testlog.info("And User clicks on Submit button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardVerifyUploadDoc", 0);
			testlog.info("Then User verifies upload scorecard document");
			break;
		}
	}

	public void uploadModalCompleteGeneric(String SheetName, int rowNum, String Commodity, String FileName,
			Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,
			Boolean IntentCheckboxRequired) throws Exception {

		uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
				VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);

		switch (Commodity) {

		case "Ratings":

			if (FileName.contains("Audit")) {
				if (!TestCaseName.contains("ScoreCardUploadAlternativeDocument")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocFirstChildCbx", 0);
				CommonMethod.ClickUnCheckbox("WPRAssignLocFirstChildCbx");
			}
			}
			rc.ScorecardConfirmLocUploadSaveButton();
			testlog.info("And User clicks on save button");
			break;

		case "Portfolio":

			if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_01_01_MeetThresholdsforParticulateMatter")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
				CommonMethod.ClickUnCheckbox("WPRAssignLocFirstChildCbx");
				testlog.info("And User checks the AssignLocation checkbox");
			}

			if (TestCaseName.equalsIgnoreCase("Portfolio_TC_07_02_UploadFileInAudit")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
				CommonMethod.ClickUnCheckbox("WPRAssignLocFirstChildCbx");
				testlog.info("And User checks the AssignLocation checkbox");
			}

			if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_01_01_MeetThresholdsforOrganicGasesFeature")) {
				CommonMethod.ClickUnCheckbox("WPRAssignLocFirstChildCbx");
			}

			if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_01_03_MeetEnhancedThresholdsforOrganicGases")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationAssignChildLocCbx", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationAssignLocCbx",
						"PortfolioScorecardValidDisable");
				testlog.info("And User checks the AssignLoccation checkbox");
			}

			rc.ScorecardConfirmLocUploadSaveButton();
			rc.uploadDocumentToastMessage();
			testlog.info("And User verifies Upload Document Successful Toast Message");
			break;
		}

	}

	public void validateUploadedDocumentTableGeneric(String SheetName, int rowNum, String Commodity,
			Boolean FileNameRequired, Boolean PartIdRequired, Boolean LocationCountRequired,
			Boolean VerificationMethodValidationRequired, Boolean StageRequired, Boolean StatusRequired,
			Boolean DeleteEditIconRequired, Boolean StageIntentRequired) throws Exception {

		switch (Commodity) {
		case "Ratings":

			break;

		case "Portfolio":
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			testlog.info("And User will be redirected to Document Upload Table page");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
			List<String> Tableval = CommonMethod.fetchTableData(PortfolioAndRatingLocAccDocumentTable);
			testlog.info("Fetching Data from Upload Table");

			if (FileNameRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(0), pathprms.get("fileName").toString(),
						"fileName in Document table data mismatch");
			}

			if (LocationCountRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(1), pathprms.get("LocationCount").toString(),
						"Assign Location in Document table data mismatch");
			}

			if (PartIdRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(2), pathprms.get("PartId").toString(),
						"PartId in Document table data mismatch");
			}

			if (VerificationMethodValidationRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(3),
						pathprms.get("VerificationMethod").toString(),
						"verificationMethod in Document table data mismatch");
			}

			if (StageRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(4), pathprms.get("Stage").toString(),
						"Stage in Document table data mismatch");
			}

			if (StageIntentRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(4), pathprms.get("StageIntent").toString(),
						"StageIntent in Document table data mismatch");
			}

			if (StatusRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(5), pathprms.get("Status").toString(),
						"Status Document table data mismatch");
			}

			if (DeleteEditIconRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
				CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
				testlog.info("And User verifies Edit Icon in Document table");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioAndRatingLocAccDocumentTableDeleteIcon", 0);
				testlog.info("And User verifies Delete Icon in Document table");
			}

			break;
		}
	}

	public void validateUploadedDocumentTableOptnGeneric(String SheetName, int rowNum, String Commodity,
			Boolean FileNameRequired, Boolean PartIdRequired, Boolean VerificationMethodValidationRequired,
			Boolean StageRequired, Boolean StatusRequired, Boolean DeleteEditIconRequired) throws Exception {

		switch (Commodity) {
		case "Ratings":

			break;

		case "Portfolio":
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			testlog.info("And User will be redirected to Document Upload Table page");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
			List<String> Tableval = CommonMethod.fetchTableData(PortfolioAndRatingLocAccDocumentTable);
			testlog.info("Fetching Data from Upload Table");

			if (FileNameRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(0), pathprms.get("fileName").toString(),
						"fileName in Document table data mismatch");
			}

			if (PartIdRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(1), pathprms.get("PartId").toString(),
						"PartId in Document table data mismatch");
			}

			if (VerificationMethodValidationRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(2),
						pathprms.get("VerificationMethod").toString(),
						"verificationMethod in Document table data mismatch");
			}

			if (StageRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(3), pathprms.get("Stage").toString(),
						"Stage in Document table data mismatch");
			}

			if (StatusRequired) {
				CommonMethod.negativesoftassertFieldValid(Tableval.get(4), pathprms.get("Status").toString(),
						"Status Document table data mismatch");
			}

			if (DeleteEditIconRequired) {

				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
				CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
				testlog.info("And User verifies Edit Icon in Document table");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioAndRatingLocAccDocumentTableDeleteIcon", 0);
				testlog.info("And User verifies Delete Icon in Document table");
			}
			pathprms.clear();
			break;
		}
	}

	public void assignLocationGeneric(String Commodity, Boolean AssignFiveLocationRequired,
			Boolean AssignFifteenLocationRequired, Boolean AssignMultipleLocationRequired,
			Boolean AssignInputLocationRequired, Boolean AssignTwentyLocationRequired) throws Exception {
		if (AssignFiveLocationRequired) {
			/** Assign 5 Location */
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Assignbutton", 0);
			Thread.sleep(1000);
			CommonMethod.JavascriptClickElement("Assignbutton");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
			CommonMethod.Robustclick("WPRAssignLocCbx", "WPRAssignDisabledbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignSavebtn", 0);
			CommonMethod.Robustclick("WPRAssignSavebtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
			CommonMethod.Robustclick("AssignLocCloseIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
		}

		if (AssignFifteenLocationRequired) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Assignbutton", 0);
			/** Assign 15 Location */
			CommonMethod.RobustclickElementVisible("WPRAssignLocbtn",
					"PortfolioScoreCardVerificationAssignChildLocCbx");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignChildLocCbx",
					0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
			CommonMethod.clickListWebelementFromRange("PortfolioScoreCardVerificationAssignLocCbxGeneral", 1, 11);
			CommonMethod.WaitUntilPresence(PortfolioScoreCardVerificationAssignLocSavebtn, 60);
			CommonMethod.Robustclick(PortfolioScoreCardVerificationAssignLocSavebtn);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
			CommonMethod.Robustclick("AssignLocCloseIcon");
			CommonMethod.WaitUntilPresence("PortfolioTaskListEditLocation", 60);
			CommonMethod.RobustclickElementVisible("PortfolioTaskListEditLocation",
					"PortfolioScoreCardVerificationAssignLocCbxGeneral");
			testlog.info("And User clicks on Edit Location Button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectDocumentScorecardNextPagnition", 0);
			CommonMethod.RobustclickElementVisible("PortfolioV2ProjectDocumentScorecardNextPagnition",
					"PortfolioScoreCardVerificationAssignLocCbxGeneral");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
			CommonMethod.clickListWebelementFromRange("PortfolioScoreCardVerificationAssignLocCbxGeneral", 1, 6);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioScoreCardVerificationAssignLocSavebtn, 0);
			CommonMethod.Robustclick(PortfolioScoreCardVerificationAssignLocSavebtn);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
			CommonMethod.Robustclick("AssignLocCloseIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
			testlog.info("And User clicks on Assign Button");
			testlog.info("And User check Assign location checkboxes");
		}

		if (AssignMultipleLocationRequired) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignbtn", 0);
			Thread.sleep(1000);
			/** Assign all the task with Assign 5 Location */
			List<WebElement> AssignButton;
			AssignButton = CommonMethod.findElements("PortfolioScoreCardVerificationAssignbtn");
			for (WebElement assignButton : AssignButton) {
				Thread.sleep(2000);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignbtn", 0);
				CommonMethod.JavascriptClickElement(assignButton);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
				CommonMethod.Robustclick("WPRAssignLocCbx", "WPRAssignDisabledbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioScoreCardVerificationAssignLocSavebtn,
						0);
				CommonMethod.Robustclick(PortfolioScoreCardVerificationAssignLocSavebtn);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
			}
		}

		if (AssignInputLocationRequired) {
			/** Assign Location with index input */
			List<WebElement> AssignButton;
			AssignButton = CommonMethod.findElements("PortfolioScoreCardVerificationAssignbtn");
			for (WebElement f : AssignButton) {
				CommonMethod.WaitUntilClickble(f, 30).click();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationAssignChildLocCbx", 0);
				CommonMethod.clickListWebelementFromRange("PortfolioScoreCardVerificationAssignLocCbxGeneral",
						Integer.parseInt(pathprms.get("StartAssignLoc").toString()),
						Integer.parseInt(pathprms.get("EndAssignLoc").toString()));
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioScoreCardVerificationAssignLocSavebtn,
						0);
				CommonMethod.Robustclick(PortfolioScoreCardVerificationAssignLocSavebtn);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
			}
		}

		if (AssignTwentyLocationRequired) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Assignbutton", 0);
			/** Assign 15 Location */
			CommonMethod.RobustclickElementVisible("WPRAssignLocbtn",
					"PortfolioScoreCardVerificationAssignChildLocCbx");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignChildLocCbx",
					0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
			CommonMethod.clickListWebelementFromRange("PortfolioScoreCardVerificationAssignLocCbxGeneral", 1, 11);
			CommonMethod.WaitUntilPresence(PortfolioScoreCardVerificationAssignLocSavebtn, 60);
			CommonMethod.Robustclick(PortfolioScoreCardVerificationAssignLocSavebtn);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
			CommonMethod.Robustclick("AssignLocCloseIcon");
			CommonMethod.WaitUntilPresence("PortfolioTaskListEditLocation", 60);
			CommonMethod.RobustclickElementVisible("PortfolioTaskListEditLocation",
					"PortfolioScoreCardVerificationAssignLocCbxGeneral");
			testlog.info("And User clicks on Edit Location Button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectDocumentScorecardNextPagnition", 0);
			CommonMethod.RobustclickElementVisible("PortfolioV2ProjectDocumentScorecardNextPagnition",
					"PortfolioScoreCardVerificationAssignLocCbxGeneral");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
			CommonMethod.clickListWebelementFromRange("PortfolioScoreCardVerificationAssignLocCbxGeneral", 1, 6);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioScoreCardVerificationAssignLocSavebtn, 0);
			CommonMethod.Robustclick(PortfolioScoreCardVerificationAssignLocSavebtn);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
			CommonMethod.Robustclick("AssignLocCloseIcon");
			testlog.info("And User clicks on Assign Button");
			testlog.info("And User check Assign location checkboxes");
		}
		pathprms.clear();
	}

	public void importLocationGeneric(String Commodity, String ImportFileName) throws Exception {
		testlog.info("Given User is on Dashboard page");
		System.out.println("ImportFileName: " + ImportFileName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioLocationLanding");
		testlog.info("When User clicks on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationsImportButton", 0);
		Thread.sleep(3000);
		CommonMethod.refreshBrowser();
		CommonMethod.RobustclickElementVisible("PortfolioLocationsImportButton", "PortfolioUploadLocationButton");
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationSelectCard", 0);
			CommonMethod.RobustclickElementVisible("LocationSelectCard", "PortfolioUploadFileNextButton");
			testlog.info("And User clicks on Import Button");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
		CommonMethod.scrolldowntoElement("PortfolioUploadLocationButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
		CommonMethod.uploadFile("PortfolioUploadLocationButton", ImportFileName, "UploadFileVerifyScorecard");
		testlog.info("And User upload Excel Location List Document");
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			if (CommonMethod.isElementsExist("PortfolioUploadFileNextButton", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadFileNextButton", 0);
				CommonMethod.click("PortfolioUploadFileNextButton");
			}
			if (CommonMethod.isElementsExist("PortfolioUnmatchFieldcbx", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUnmatchFieldcbx", 0);
				CommonMethod.ClickCheckbox("PortfolioUnmatchFieldcbx");
				testlog.info("And User checks the Unmatch checkbox");
			}
		}
		if (CommonMethod.isElementsExist("PortfolioUploadFileNextButton", 5)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadFileNextButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioUploadFileNextButton", "PortfolioFinishImportButton");
		}
		testlog.info("And User clicks on Next Button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioFinishImportButton", 0);
		testlog.info("And User clicks on Finish Button");
		CommonMethod.RobustclickElementVisible("PortfolioFinishImportButton", "PortfolioImportCloseButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportCloseButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioImportCloseButton", "PortfolioLocationLanding");
		testlog.info("And User clicks on Close Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
		testlog.info("Then User will be redirected to Location list page");
		testlog.pass("**Imported Locations successfully**");
	}

	public void ReplaceFirstScreenGenericDocumentUpload(String Commodity, Boolean PartNameRequired, String FileName,
			Boolean NoteRequired) throws Exception {

		switch (Commodity) {
		case "Portfolio":
		
			rc.documentTableReplaceButton();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceBanner", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReplaceBanner"),
					"Replacing this document will archive the original document.", "ReplaceBanner doesn't match");
			testlog.info("ReplaceBanner: " + CommonMethod.getattributeValueByTextContent("ReplaceBanner"));
			testlog.info("And User verifies VerificationMethod");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScoreVerifyUploadVerificationMethod"),
					pathprms.get("VerificationMethod").toString(), "Verification Method doesn't match");
			testlog.info("VerificationMethod: "
					+ CommonMethod.getattributeValueByTextContent("PortfolioScoreVerifyUploadVerificationMethod"));
			testlog.info("And User verifies VerificationMethod");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardDocumentType"),
					pathprms.get("DocumentType").toString(), "Document Type doesn't match");
			testlog.info(
					"DocumentType: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardDocumentType"));
			testlog.info("And User verifies DocumentType");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadFeatureName"),
					pathprms.get("FeatureName").toString(), "Feature Name doesn't match");
			testlog.info("FeatureName: "
					+ CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadFeatureName"));
			testlog.info("And User verifies PartName");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadPartName", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadPartName"),
					pathprms.get("PartName").toString(), "PartName doesn't match");
			testlog.info(
					"PartName: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadPartName"));
			testlog.info("And User verifies PartNo.");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadSpaceType", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadSpaceType"),
					pathprms.get("SpaceType").toString(), "SpaceType doesn't match");
			testlog.info(
					"SpaceType: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadSpaceType"));
			testlog.info("And User verifies SpaceType");
			if (TestCaseName.contains("FeatureUploadReplaceInDocumentLibrary")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
				CommonMethod.Robustclick("RemoveOption");
			}
			if (TestCaseName.contains("PostFeatureScorecardUploadReplace")
					|| TestCaseName.contains("PostAlternativeScorecardUploadReplace") || TestCaseName.contains("UploadDocumentLibraryReplace")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
				CommonMethod.Robustclick("RemoveOption");
				if (CommonMethod.isElementsExist("ScorecardAddedRemoveOptionPopUpContinuebtn", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedRemoveOptionPopUpContinuebtn",
						0);
				CommonMethod.JavascriptClickElement("ScorecardAddedRemoveOptionPopUpContinuebtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ScorecardAddedRemoveOptionPopUpContinuebtn",
						1);
				}
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonDisabled", 1);
			CommonMethod.assertisElementPresentTrue("RemoveButtonDisabled", "RemoveButton is visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveButtonDisabledTooltip", 0);
			String actualDisabledRemoveBtnToolTip = CommonMethod
					.getattributeValueByTextContent("RemoveButtonDisabledTooltip");
			actualDisabledRemoveBtnToolTip = actualDisabledRemoveBtnToolTip.replaceAll("\\s+", " ").trim();
			CommonMethod.negativesoftassertFieldValid(actualDisabledRemoveBtnToolTip,
					"This option can't be removed until another is added, as the document requires at least one option.",
					"Disabled Remove Button Tooltip does not matched");
			if (PartNameRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadSpaceType");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				testlog.info("And User clicks on Add feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature",
						pathprms.get("PartId").toString());
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.JavascriptClickElement("PortfolioScoreCardVerificationAddPartbtn");
				
				Thread.sleep(30000);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectSpaceType");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonNotDisabled", 2);
				int RemoveButtonNotDisabled = CommonMethod.ElementSize("RemoveButtonNotDisabled");
				CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(RemoveButtonNotDisabled), "2",
						"RemoveButton NotDisabled Count doesn't match");
				testlog.info("And User verifies Removelink should be visible");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("RemoveButtonDisabled");
				CommonMethod.assertisElementPresentFalse("RemoveButtonDisabled", "RemoveButtonDisabled is visible");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
			CommonMethod.uploadFile("DocumentsUpload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");
			if (NoteRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValue("PortfolioScoreCardVerificationAddNote"),
						pathprms.get("Note").toString(), "Note Comment doesn't match");
				testlog.info("And User verifies data in note field");
			}
			rc.ScorecardUploadUpdateSaveButton();
			break;
			
		case "Ratings":
			rc.documentTableReplaceButton();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceBanner", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReplaceBanner"),
					"Replacing this document will archive the original document.", "ReplaceBanner doesn't match");
			testlog.info("ReplaceBanner: " + CommonMethod.getattributeValueByTextContent("ReplaceBanner"));
			testlog.info("And User verifies VerificationMethod");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScoreVerifyUploadVerificationMethod"),
					pathprms.get("VerificationMethod").toString(), "Verification Method doesn't match");
			testlog.info("VerificationMethod: "
					+ CommonMethod.getattributeValueByTextContent("PortfolioScoreVerifyUploadVerificationMethod"));
			testlog.info("And User verifies VerificationMethod");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardDocumentType"),
					pathprms.get("DocumentType").toString(), "Document Type doesn't match");
			testlog.info(
					"DocumentType: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardDocumentType"));
			testlog.info("And User verifies DocumentType");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("WERScorecardUploadFeatureName"),
					pathprms.get("FeatureName").toString(), "Feature Name doesn't match");
			testlog.info("FeatureName: "
					+ CommonMethod.getattributeValueByTextContent("WERScorecardUploadFeatureName"));
			testlog.info("And User verifies PartName");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadPartName", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadPartName"),
					pathprms.get("PartName").toString(), "PartName doesn't match");
			testlog.info(
					"PartName: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadPartName"));
			testlog.info("And User verifies PartNo.");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadSpaceType", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadSpaceType"),
					pathprms.get("SpaceType").toString(), "SpaceType doesn't match");
			testlog.info(
					"SpaceType: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadSpaceType"));
			testlog.info("And User verifies SpaceType");
			if (TestCaseName.contains("FeatureUploadReplaceInDocumentLibrary")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
				CommonMethod.Robustclick("RemoveOption");
			}
			if (TestCaseName.contains("PostFeatureScorecardUploadReplace")
					|| TestCaseName.contains("PostAlternativeScorecardUploadReplace") || TestCaseName.contains("UploadDocumentLibraryReplace")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
				CommonMethod.Robustclick("RemoveOption");
				if (CommonMethod.isElementsExist("ScorecardAddedRemoveOptionPopUpContinuebtn", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedRemoveOptionPopUpContinuebtn",
						0);
				CommonMethod.JavascriptClickElement("ScorecardAddedRemoveOptionPopUpContinuebtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ScorecardAddedRemoveOptionPopUpContinuebtn",
						1);
				}
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonDisabled", 1);
			CommonMethod.assertisElementPresentTrue("RemoveButtonDisabled", "RemoveButton is visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveButtonDisabledTooltip", 0);
			String actualDisabledRemoveBtnToolTip1 = CommonMethod
					.getattributeValueByTextContent("RemoveButtonDisabledTooltip");
			actualDisabledRemoveBtnToolTip = actualDisabledRemoveBtnToolTip1.replaceAll("\\s+", " ").trim();
			CommonMethod.negativesoftassertFieldValid(actualDisabledRemoveBtnToolTip,
					"This option can't be removed until another is added, as the document requires at least one option.",
					"Disabled Remove Button Tooltip does not matched");
			if (PartNameRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadSpaceType");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				testlog.info("And User clicks on Add feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature",
						pathprms.get("PartId").toString());
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart", "PortfolioScorecardUploadFeatureRemove");
				CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadAddfeature","PortfolioScoreCardVerificationSelectFeature");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonNotDisabled", 2);
				int RemoveButtonNotDisabled = CommonMethod.ElementSize("RemoveButtonNotDisabled");
				CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(RemoveButtonNotDisabled), "2",
						"RemoveButton NotDisabled Count doesn't match");
				testlog.info("And User verifies Removelink should be visible");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("RemoveButtonDisabled");
				CommonMethod.assertisElementPresentFalse("RemoveButtonDisabled", "RemoveButtonDisabled is visible");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
			CommonMethod.uploadFile("DocumentsUpload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");
			if (NoteRequired) {
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValue("PortfolioScoreCardVerificationAddNote"),
						pathprms.get("Note").toString(), "Note Comment doesn't match");
				testlog.info("And User verifies data in note field");
			}
			rc.ScorecardUploadUpdateSaveButton();
			break;
		}
	}

	public void ReplaceSecondScreenGenericDocumentUpload(String Commodity, String LocationLabel,String FileName) throws Exception {

		switch (Commodity) {
		case "Portfolio":
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceBackBtn", 0);
			CommonMethod.Robustclick("ReplaceBackBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("UploadFileVerifyScorecard", 1);
			CommonMethod.assertisElementPresentTrue("UploadFileVerifyScorecard", "Remove button is not present ");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValue("PortfolioScoreCardVerificationAddNote"),
					pathprms.get("Note").toString(), "Note Comment doesn't match");
			testlog.info("And User verifies data in note field");
			rc.ScorecardUploadSaveButton();
			testlog.info("And User verifies Replace BackButton and verifies the Added document");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocationLoading", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectIndex2CheckBox", 0);
			CommonMethod.ClickUnCheckbox("SelectIndex2CheckBox");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SaveAndExitbtn");
			CommonMethod.assertisElementPresentFalse("SaveAndExitbtn", "SaveAndExitbtn is visible");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SaveAndContinueUpdatebtn");
			CommonMethod.assertisElementPresentFalse("SaveAndContinueUpdatebtn", "SaveAndContinueUpdatebtn is visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardLocationCountLabel", 0);
			String actualLocationCountLabel = CommonMethod
					.getattributeValueByTextContent("V2ProjectScorecardLocationCountLabel").trim();
			CommonMethod.negativesoftassertFieldValid(actualLocationCountLabel.replaceAll("\\s+", " "), LocationLabel,
					"Selected location count does not matched");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceUploadbtn", 0);
			CommonMethod.Robustclick("ReplaceUploadbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
			break;
		
	case "Ratings":
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceBackBtn", 0);
		CommonMethod.Robustclick("ReplaceBackBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("UploadFileVerifyScorecard", 1);
		CommonMethod.assertisElementPresentTrue("UploadFileVerifyScorecard", "Remove button is not present ");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("PortfolioScoreCardVerificationAddNote"),
				pathprms.get("Note").toString(), "Note Comment doesn't match");
		testlog.info("And User verifies data in note field");
		rc.ScorecardUploadSaveButton();
		testlog.info("And User verifies Replace BackButton and verifies the Added document");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocationLoading", 1);
		if (FileName.contains("Audit")){
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
			CommonMethod.ClickCheckbox("WPRAssignLocCbx");	
		}
		else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectIndex2CheckBox", 0);
		CommonMethod.ClickUnCheckbox("SelectIndex2CheckBox");
		}
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SaveAndExitbtn");
		CommonMethod.assertisElementPresentFalse("SaveAndExitbtn", "SaveAndExitbtn is visible");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SaveAndContinueUpdatebtn");
		CommonMethod.assertisElementPresentFalse("SaveAndContinueUpdatebtn", "SaveAndContinueUpdatebtn is visible");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardLocationCountLabel", 0);
		String actualLocationCountLabel1 = CommonMethod
				.getattributeValueByTextContent("V2ProjectScorecardLocationCountLabel").trim();
		CommonMethod.negativesoftassertFieldValid(actualLocationCountLabel1.replaceAll("\\s+", " "), LocationLabel,
				"Selected location count does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceUploadbtn", 0);
		CommonMethod.Robustclick("ReplaceUploadbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
		break;
	}


	}

	public void ReplaceOneOptionGenericDocumentUpload(String Commodity, String FileName, String LocationLabel) throws Exception {

		switch (Commodity) {
		case "Portfolio":

			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
			CommonMethod.Robustclick("RemoveOption");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonDisabled", 1);
			CommonMethod.assertisElementPresentTrue("RemoveButtonDisabled", "RemoveButton is visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveButtonDisabledTooltip", 0);
			String actualDisabledRemoveBtnToolTip = CommonMethod
					.getattributeValueByTextContent("RemoveButtonDisabledTooltip");
			actualDisabledRemoveBtnToolTip = actualDisabledRemoveBtnToolTip.replaceAll("\\s+", " ").trim();
			CommonMethod.negativesoftassertFieldValid(actualDisabledRemoveBtnToolTip,
					"This option can't be removed until another is added, as the document requires at least one option.",
					"Disabled Remove Button Tooltip does not matched");
			CommonMethod.uploadFile("DocumentsUpload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");
			rc.ScorecardUploadUpdateSaveButton();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceBackBtn", 0);
			CommonMethod.Robustclick("ReplaceBackBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("UploadFileVerifyScorecard", 1);
			CommonMethod.assertisElementPresentTrue("UploadFileVerifyScorecard", "Remove button is not present ");
			rc.ScorecardUploadSaveButton();
			testlog.info("And User verifies Replace BackButton and verifies the Added document");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocationLoading", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectIndex2CheckBox", 0);
			CommonMethod.ClickCheckbox("SelectIndex2CheckBox");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SaveAndExitbtn");
			CommonMethod.assertisElementPresentFalse("SaveAndExitbtn", "SaveAndExitbtn is visible");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SaveAndContinueUpdatebtn");
			CommonMethod.assertisElementPresentFalse("SaveAndContinueUpdatebtn", "SaveAndContinueUpdatebtn is visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardLocationCountLabel", 0);
			String actualLocationCountLabel = CommonMethod
					.getattributeValueByTextContent("V2ProjectScorecardLocationCountLabel").trim();
			CommonMethod.negativesoftassertFieldValid(actualLocationCountLabel.replaceAll("\\s+", " "), LocationLabel,
					"Selected location count does not matched");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceUploadbtn", 0);
			CommonMethod.Robustclick("ReplaceUploadbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			testlog.info("And User will be redirected to Document Upload Table page");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
			List<String> Tableval = CommonMethod.fetchTableData(PortfolioAndRatingLocAccDocumentTable);
			testlog.info("Fetching Data from Upload Table");
			CommonMethod.negativesoftassertFieldValid(Tableval.get(1), LocationLabel,"Assign Location in Document table data mismatch");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioValidRemovedPart");
			CommonMethod.negativeAssertElementNotPresentFalse("PortfolioValidRemovedPart", "Removed Part is visible");
		}
	}
	
	public void ReplaceSubscribeLocationFirstScreenGenericDocumentUpload(String Commodity, Boolean PartNameRequired, String FileName,
			Boolean NoteRequired) throws Exception {

		switch (Commodity) {
		case "Portfolio":
		
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceBanner", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReplaceBanner"),
					"Replacing this document will archive the original document.", "ReplaceBanner doesn't match");
			testlog.info("ReplaceBanner: " + CommonMethod.getattributeValueByTextContent("ReplaceBanner"));
			testlog.info("And User verifies VerificationMethod");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScoreVerifyUploadVerificationMethod"),
					pathprms.get("VerificationMethod").toString(), "Verification Method doesn't match");
			testlog.info("VerificationMethod: "
					+ CommonMethod.getattributeValueByTextContent("PortfolioScoreVerifyUploadVerificationMethod"));
			testlog.info("And User verifies VerificationMethod");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardDocumentType"),
					pathprms.get("DocumentType").toString(), "Document Type doesn't match");
			testlog.info(
					"DocumentType: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardDocumentType"));
			testlog.info("And User verifies DocumentType");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadFeatureName"),
					pathprms.get("FeatureName").toString(), "Feature Name doesn't match");
			testlog.info("FeatureName: "
					+ CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadFeatureName"));
			testlog.info("And User verifies PartName");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadPartName", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadPartName"),
					pathprms.get("PartName").toString(), "PartName doesn't match");
			testlog.info(
					"PartName: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadPartName"));
			testlog.info("And User verifies PartNo.");
			if (!(TestCaseName.contains("AlternativeScorecardUpload") || TestCaseName.contains("UploadAlternativeDocumentLibraryReplace"))) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadSpaceType", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadSpaceType"),
					pathprms.get("SpaceType").toString(), "SpaceType doesn't match");
			testlog.info(
					"SpaceType: " + CommonMethod.getattributeValueByTextContent("PortfolioScorecardUploadSpaceType"));
			testlog.info("And User verifies SpaceType");
			}
			if (TestCaseName.contains("FeatureUploadReplaceInDocumentLibrary")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
				CommonMethod.JavascriptClickElement("RemoveOption");
			}
			if (TestCaseName.contains("PostFeatureScorecardUploadReplace")
					|| TestCaseName.contains("PostAlternativeScorecardUploadReplace") || TestCaseName.contains("UploadDocumentLibraryReplace") || TestCaseName.contains("UploadAlternativeDocumentLibraryReplace")) {
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadFeatureName");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
				CommonMethod.Robustclick("RemoveOption");
				if (CommonMethod.isElementsExist("ScorecardAddedRemoveOptionPopUpContinuebtn", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedRemoveOptionPopUpContinuebtn",
						0);
				CommonMethod.JavascriptClickElement("ScorecardAddedRemoveOptionPopUpContinuebtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ScorecardAddedRemoveOptionPopUpContinuebtn",
						1);
				}
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonDisabled", 1);
			CommonMethod.negativeAssertElementPresentTrue("RemoveButtonDisabled", "RemoveButton is visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveButtonDisabledTooltip", 0);
			String actualDisabledRemoveBtnToolTip = CommonMethod
					.getattributeValueByTextContent("RemoveButtonDisabledTooltip");
			actualDisabledRemoveBtnToolTip = actualDisabledRemoveBtnToolTip.replaceAll("\\s+", " ").trim();
			CommonMethod.negativesoftassertFieldValid(actualDisabledRemoveBtnToolTip,
					"This option can't be removed until another is added, as the document requires at least one option.",
					"Disabled Remove Button Tooltip does not matched");
			if (PartNameRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadSpaceType");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				testlog.info("And User clicks on Add feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature",
						pathprms.get("PartId").toString());
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.JavascriptClickElement("PortfolioScoreCardVerificationAddPartbtn");
				CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectSpaceType");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonNotDisabled", 2);
				int RemoveButtonNotDisabled = CommonMethod.ElementSize("RemoveButtonNotDisabled");
				CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(RemoveButtonNotDisabled), "2",
						"RemoveButton NotDisabled Count doesn't match");
				testlog.info("And User verifies Removelink should be visible");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("RemoveButtonDisabled");
				CommonMethod.negativeAssertElementNotPresentFalse("RemoveButtonDisabled", "RemoveButtonDisabled is visible");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
			CommonMethod.uploadFile("DocumentsUpload", FileName, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");
			if (NoteRequired) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValue("PortfolioScoreCardVerificationAddNote"),
						pathprms.get("Note").toString(), "Note Comment doesn't match");
				testlog.info("And User verifies data in note field");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadDocbtn",
					0);
			CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadDocbtn",
					"PortfolioScoreCardVerificationAddNote");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScoreCardVerificationUploadDocbtn",1);
			break;
		}
		}
	
	
	public void filterGeneric(String Commodity, String optionXpath,String optionCheckboxXpath, String rowCountXpath,String expectedResult, String ScorecardCondition) throws Exception {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(optionXpath, 0);
		CommonMethod.RobustclickElementVisible(optionXpath, optionCheckboxXpath);
		testlog.info("And User select the Response Filter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(optionCheckboxXpath, 0);
		CommonMethod.ClickCheckbox(optionCheckboxXpath);
		testlog.info("And User check the Yes Filter checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardApplybutton", 0);
		CommonMethod.JavascriptClickElement("V2ProjectScorecardApplybutton");
		testlog.info("And User clicks on Apply button");
		if (CommonMethod.isElementsExist("ScorecardFeatureLoading", 20)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ScorecardFeatureLoading", 1);
		}
		switch (Commodity) {
		case "SingleAsset":
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardPartCount",
					Integer.parseInt(expectedResult));
			int featureCount = CommonMethod.ElementSize("V2ProjectScorecardPartCount");
			testlog.info("Response Feature Count: " + featureCount);
			if(ScorecardCondition.equalsIgnoreCase("true")) {
			rc.validateFeatureCount(Integer.parseInt(expectedResult), featureCount,
					"Option filter Count: "+ optionXpath);
			}
			else {
				 CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(featureCount),expectedResult, "Option filter Count: "+optionXpath +" ActualCount: "+featureCount +" , ExpectedCount: "+ expectedResult);
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(optionXpath, 0);
			CommonMethod.JavascriptClickElement(optionXpath);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardClearbutton", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardClearbutton", "V2ProjectScoreCardFilterOption");
			testlog.info("And User clicks on Clear button");
			break;
		
	case "Portfolio":
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScoreCardFeature",
				Integer.parseInt(expectedResult));
		int featureCount1 = CommonMethod.ElementSize("PortfolioScoreCardFeature");
		testlog.info("Response Feature Count: " + featureCount1);
		if(ScorecardCondition.equalsIgnoreCase("true")) {
		rc.validateFeatureCount(Integer.parseInt(expectedResult), featureCount1,
				"Option filter Count: "+ optionXpath);
		}
		else {
			 CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(featureCount1),expectedResult, "Option filter Count: "+optionXpath +" ActualCount: "+featureCount1 +" , ExpectedCount: "+ expectedResult);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(optionXpath, 0);
		CommonMethod.JavascriptClickElement(optionXpath);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardClearbutton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardClearbutton", "V2ProjectScoreCardFilterOption");
		testlog.info("And User clicks on Clear button");
		break;
	
case "Ratings":

	if(ScorecardCondition.equalsIgnoreCase("true")) {
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RatingFeatureName",
				Integer.parseInt(expectedResult));
	int featureCount2 = CommonMethod.ElementSize("RatingFeatureName");
	testlog.info("Response Feature Count: " + featureCount2);
	rc.validateFeatureCount( featureCount2,Integer.parseInt(expectedResult),
			"Option filter Count: "+ optionXpath);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "PortfolioFliterOptionClear");
	testlog.info("And User clicks on Filter button");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(optionXpath, 0);
	CommonMethod.JavascriptClickElement(optionXpath);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardClearbutton", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectScorecardClearbutton", "V2ProjectScoreCardFilterOption");
	testlog.info("And User clicks on Clear button");
	}
	else {
		CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioScorecardFeatureTableCount", expectedResult, 300);
		String featureCount3 = CommonMethod.getattributeValueByTextContent("PortfolioScorecardFeatureTableCount");
		testlog.info("Response Feature Count: " + featureCount3);
		 CommonMethod.negativesoftassertFieldValid(String.valueOf(featureCount3),expectedResult, "Option filter Count: "+optionXpath +" ActualCount: "+featureCount3 +" , ExpectedCount: "+ expectedResult);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardClearbutton");
		 testlog.info("And User clicks on Filter button");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardClearbutton", 0);
		 CommonMethod.RobustclickElementVisible("V2ProjectScorecardClearbutton", "V2ProjectScoreCardFilterOption");
		testlog.info("And User clicks on Clear button");
	}
	break;
}
		testlog.info("Then User verifies Scorecard Feature Count");
		testlog.pass("**Verifies filter " + optionXpath + " options successfully**");
	}
}