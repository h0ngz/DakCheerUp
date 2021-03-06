package com.fourdrone.dakcheerup.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fourdrone.dakcheerup.domain.Resume;
import com.fourdrone.dakcheerup.domain.member.Member;
import com.fourdrone.dakcheerup.domain.resume.*;
import com.fourdrone.dakcheerup.service.AccountService;
import com.fourdrone.dakcheerup.service.ResumeService;

@Controller
@RequestMapping("/resume")
public class ResumeController {

	@Autowired private ResumeService resumeService;
	@Autowired private HttpSession session;
		
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getResume(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		//기본인적사항 불러오기
		Profile profile = this.resumeService.getProfile(memberId);
	    model.addAttribute("profile", profile);  
	    
		//형제관계 불러오기
		Brother brother = this.resumeService.getBrother(memberId);
	    model.addAttribute("brother", brother);	 
	    //가족사항 리스트 불러오기
	    List<Family> familyList = this.resumeService.getFamilyList(memberId);
	    model.addAttribute("familyList", familyList);
	    //병역 불러오기
	    Military military = this.resumeService.getMilitary(memberId);
	    model.addAttribute("military", military);
	    
	    //고등학교 불러오기
		AcademicHigh academicHigh = this.resumeService.getAcademicHigh(memberId);
	    model.addAttribute("academicHigh", academicHigh);	 
	    //대학교 리스트 불러오기
	    List<AcademicUniv> academicUnivList = this.resumeService.getAcademicUnivList(memberId);
	    model.addAttribute("academicUnivList", academicUnivList);
	    
	    //OA 능력불러오기 
	    OA oa = this.resumeService.getOA(memberId);
	    model.addAttribute("oa", oa);  
	    
	    List<License> licenseList = this.resumeService.getLicenseList(memberId);
	    model.addAttribute("licenseList", licenseList);  
	    
	    List<LanguageAbility> languageAbilityList = this.resumeService.getLanguageAbilityList(memberId);
	    model.addAttribute("languageAbilityList", languageAbilityList);	 
	    
	    List<LanguageExam> languageExamList = this.resumeService.getLanguageExamList(memberId);
	    model.addAttribute("languageExamList", languageExamList);
	    
	    List<Award> awardList = this.resumeService.getAwardList(memberId);
	    model.addAttribute("awardList", awardList);	
	    
	    List<Career> careerList = this.resumeService.getCareerList(memberId);
	    model.addAttribute("careerList", careerList);	
	    
	    List<Voluntary> voluntaryList = this.resumeService.getVoluntaryList(memberId);
	    model.addAttribute("voluntaryList", voluntaryList);
	    
	    List<Education> educationList = this.resumeService.getEducationList(memberId);
	    model.addAttribute("educationList", educationList);
	    
	    List<Project> projectList = this.resumeService.getProjectList(memberId);
	    model.addAttribute("projectList", projectList);
	    
	    List<Write> writeList = this.resumeService.getWriteList(memberId);
	    model.addAttribute("writeList", writeList);
	    
	    List<Global> globalList = this.resumeService.getGlobalList(memberId);
	    model.addAttribute("globalList", globalList);
	    
	    Swot swot = this.resumeService.getSwot(memberId);
	    model.addAttribute("swot", swot);
	    
		
	    return "resume/resume";
	}
	
	// 기본인적사항  불러오기
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String getResumeProfile(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		Profile profile = this.resumeService.getProfile(memberId);
	    model.addAttribute("profile", profile);  
	    createLoadMsg(model);
	    
	    return "resume/resume-profile";
	}
	
	// 기본인적사항 데이터 처리
	@RequestMapping(value ="/profile", method = RequestMethod.POST)
    public String postResumeProfile(@ModelAttribute("profile") Profile profile) {  	    	
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
    	// PROFILE 테이블 업데이트
		
		profile.setMemberId(memberId);
    	profile.setProfileRegDate(new Timestamp(System.currentTimeMillis()));
        this.resumeService.modProfile(profile);
        session.setAttribute("loadMsg", "save");
                
        return "resume/resume-profile";
    }
	
	// 추가인적사항 ( 형제관계, 가족관계, 병역사항 ) 
	@RequestMapping(value="/addtion-profile", method = RequestMethod.GET)
	public String getResumeAddtionProfile(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		//형제관계 불러오기
		Brother brother = this.resumeService.getBrother(memberId);
	    model.addAttribute("brother", brother);	 
	    //가족사항 리스트 불러오기
	    List<Family> familyList = this.resumeService.getFamilyList(memberId);
	    model.addAttribute("familyList", familyList);
	    //병역 불러오기
	    Military military = this.resumeService.getMilitary(memberId);
	    model.addAttribute("military", military);	
	    
	    createLoadMsg(model);
	    
	    return "resume/resume-addtion-profile";
	}
	
	// 추가인적사항(형제관계, 가족관계, 병역사항) 데이터 처리
	@RequestMapping(value="/addtion-profile", method = RequestMethod.POST)
	public String postResumeAddtionProfile(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// BROTHER 테이블 업데이트
		Brother brother = new Brother();
		brother.setMemberId(memberId);
		brother.setBrotherRegDate(new Timestamp(System.currentTimeMillis()));
		brother.setBrotherMale(request.getParameter("brotherMale"));
		brother.setBrotherFemale(request.getParameter("brotherFemale"));		
		brother.setBrotherMe(request.getParameter("brotherMe"));		
		this.resumeService.modBrother(brother);
		
		// FAMILY 항목 수정 
		String[] familyNoList = request.getParameterValues("familyNo");
		String[] familyRelationList = request.getParameterValues("familyRelation");
		String[] familyNameList = request.getParameterValues("familyName");
		String[] familyAgeList = request.getParameterValues("familyAge");
		String[] familyJobList = request.getParameterValues("familyJob");
		String[] familyLiveList = request.getParameterValues("familyLive");
		
		if(request.getParameterValues("familyNoList") != null)
		{
			for(int i=0; i<familyNoList.length; i++)
			{
				Family family = new Family();
				family.setFamilyNo(Integer.parseInt(familyNoList[i]));
				family.setMemberId(memberId);			
				family.setFamilyRegDate(new Timestamp(System.currentTimeMillis()));
				family.setFamilyRelation(familyRelationList[i]);
				family.setFamilyName(familyNameList[i]);			
				family.setFamilyAge(familyAgeList[i]);		
				family.setFamilyJob(familyJobList[i]);
				family.setFamilyLive(familyLiveList[i]);		
				this.resumeService.modFamily(family);
			}
		}
		
		//  Family 항목 추가
		if(request.getParameterValues("newFamilyRelation") != null)
		{
			String[] newFamilyRelationList = request.getParameterValues("newFamilyRelation");
			String[] newFamilyNameList = request.getParameterValues("newFamilyName");
			String[] newFamilyAgeList = request.getParameterValues("newFamilyAge");
			String[] newFamilyJobList = request.getParameterValues("newFamilyJob");
			String[] newFamilyLiveList = request.getParameterValues("newFamilyLive");
			
			
			for(int i=0; i<newFamilyRelationList.length; i++)
			{
				Family family = new Family();
				family.setMemberId(memberId);			
				family.setFamilyRegDate(new Timestamp(System.currentTimeMillis()));
				family.setFamilyRelation(newFamilyRelationList[i]);
				family.setFamilyName(newFamilyNameList[i]);			
				family.setFamilyAge(newFamilyAgeList[i]);		
				family.setFamilyJob(newFamilyJobList[i]);
				family.setFamilyLive(newFamilyLiveList[i]);		
				this.resumeService.addFamily(family);
			}
		}
		
		
		// MILITARY 테이블 업데이트
		Military military = new Military();
		military.setMemberId(memberId);
		military.setMilitaryRegDate(new Timestamp(System.currentTimeMillis()));
		military.setMilitaryPeriod(request.getParameter("militaryPeriod"));		
		military.setMilitarySection(request.getParameter("militarySection"));
		military.setMilitaryGroup(request.getParameter("militaryGroup"));		
		military.setMilitaryRank(request.getParameter("militaryRank"));		
		military.setMilitaryWork(request.getParameter("militaryWork"));		
		military.setMilitaryReason(request.getParameter("militaryReason"));	
		this.resumeService.modMilitary(military);
		
		session.setAttribute("loadMsg", "save");
		
		return "redirect:/resume/addtion-profile";
	}
	
	// 추가인적사항 가족관계 삭제
	@RequestMapping(value="/addtion-profile/delFamily", method = RequestMethod.GET)
	public String delResumeAddtionProfileFamily(@RequestParam("familyNo") int familyNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");		
		Family family = this.resumeService.getFamily(familyNo);
		if(family.getMemberId().equals(memberId))
			this.resumeService.delFamily(familyNo);
		
		session.setAttribute("loadMsg", "delete");
	    
	    return "redirect:/resume/addtion-profile";
	}
	
	
	// 학력사항 불러오기
	@RequestMapping(value="/academic", method = RequestMethod.GET)
	public String getResumeAcademic(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		//고등학교 불러오기
		AcademicHigh academicHigh = this.resumeService.getAcademicHigh(memberId);
	    model.addAttribute("academicHigh", academicHigh);	 
	    //대학교 리스트 불러오기
	    List<AcademicUniv> academicUnivList = this.resumeService.getAcademicUnivList(memberId);
	    model.addAttribute("academicUnivList", academicUnivList);
	    	    	    
	    createLoadMsg(model);
	    return "resume/resume-academic";
	}
	
	// 학력사항 (고등학교, 대학교) 데이터 처리
	@RequestMapping(value="/academic", method = RequestMethod.POST)
	public String postResumeAcademic(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		//고등학교 테이블 업데이트
		AcademicHigh academicHigh = new AcademicHigh();
		academicHigh.setMemberId(memberId);
		academicHigh.setAcademicHighRegDate(new Timestamp(System.currentTimeMillis()));
		academicHigh.setAcademicHighArea(request.getParameter("academicHighArea"));
		academicHigh.setAcademicHighMajor(request.getParameter("academicHighMajor"));
		academicHigh.setAcademicHighName(request.getParameter("academicHighName"));
		academicHigh.setAcademicHighPeriod(request.getParameter("academicHighPeriod"));
		academicHigh.setAcademicHighSection(request.getParameter("academicHighSection"));
		this.resumeService.modAcademicHigh(academicHigh);
		
		
		// 대학교 항목 업데이트
		String[] academicUnivNoList = request.getParameterValues("academicUnivNo");
		String[] academicUnivPeriodFirstList = request.getParameterValues("academicUnivPeriodFirst");
		String[] academicUnivPeriodFirstOptionList = request.getParameterValues("academicUnivPeriodFirstOption");
		String[] academicUnivPeriodLastList = request.getParameterValues("academicUnivPeriodLast");
		String[] academicUnivPeriodLastOptionList = request.getParameterValues("academicUnivPeriodLastOption");
		String[] academicUnivDegreeList = request.getParameterValues("academicUnivDegree");
		String[] academicUnivNameList = request.getParameterValues("academicUnivName");
		String[] academicUnivMajorList = request.getParameterValues("academicUnivMajor");
		String[] academicUnivScoreList = request.getParameterValues("academicUnivScore");
		String[] academicUnivScoreOptionList = request.getParameterValues("academicUnivScoreOption");
		String[] academicUnivDoubleNameList = request.getParameterValues("academicUnivDoubleName");
		String[] academicUnivDoubleOptionList = request.getParameterValues("academicUnivDoubleOption");
		

		if(academicUnivNoList != null)
		{
			for(int i=0; i<academicUnivNoList.length; i++)
			{
				AcademicUniv academicUniv = new AcademicUniv();
				academicUniv.setMemberId(memberId);
				academicUniv.setAcademicUnivNo(Integer.parseInt(academicUnivNoList[i]));
				academicUniv.setAcademicUnivRegDate(new Timestamp(System.currentTimeMillis()));
				academicUniv.setAcademicUnivPeriodFirst(academicUnivPeriodFirstList[i]);
				academicUniv.setAcademicUnivPeriodFirstOption(academicUnivPeriodFirstOptionList[i]);
				academicUniv.setAcademicUnivPeriodLast(academicUnivPeriodLastList[i]);
				academicUniv.setAcademicUnivPeriodLastOption(academicUnivPeriodLastOptionList[i]);
				academicUniv.setAcademicUnivDegree(academicUnivDegreeList[i]);
				academicUniv.setAcademicUnivName(academicUnivNameList[i]);
				academicUniv.setAcademicUnivMajor(academicUnivMajorList[i]);
				academicUniv.setAcademicUnivScore(academicUnivScoreList[i]);
				academicUniv.setAcademicUnivScoreOption(academicUnivScoreOptionList[i]);
				academicUniv.setAcademicUnivDoubleName(academicUnivDoubleNameList[i]);
				academicUniv.setAcademicUnivDoubleOption(academicUnivDoubleOptionList[i]);
				this.resumeService.modAcademicUniv(academicUniv);
				System.out.println("업데이트 2");
			}
		}
		
		// 대학교 항목 추가
		String[] newAcademicUnivPeriodFirstList = request.getParameterValues("newAcademicUnivPeriodFirst");
		String[] newAcademicUnivPeriodFirstOptionList = request.getParameterValues("newAcademicUnivPeriodFirstOption");
		String[] newAcademicUnivPeriodLastList = request.getParameterValues("newAcademicUnivPeriodLast");
		String[] newAcademicUnivPeriodLastOptionList = request.getParameterValues("newAcademicUnivPeriodLastOption");
		String[] newAcademicUnivDegreeList = request.getParameterValues("newAcademicUnivDegree");
		String[] newAcademicUnivNameList = request.getParameterValues("newAcademicUnivName");
		String[] newAcademicUnivMajorList = request.getParameterValues("newAcademicUnivMajor");
		String[] newAcademicUnivScoreList = request.getParameterValues("newAcademicUnivScore");
		String[] newAcademicUnivScoreOptionList = request.getParameterValues("newAcademicUnivScoreOption");
		String[] newAcademicUnivDoubleNameList = request.getParameterValues("newAcademicUnivDoubleName");
		String[] newAcademicUnivDoubleOptionList = request.getParameterValues("newAcademicUnivDoubleOption");
		

		if(newAcademicUnivPeriodFirstList != null)
		{
			for(int i=0; i<newAcademicUnivPeriodFirstList.length; i++)
			{
				AcademicUniv academicUniv = new AcademicUniv();
				academicUniv.setMemberId(memberId);
				academicUniv.setAcademicUnivRegDate(new Timestamp(System.currentTimeMillis()));
				academicUniv.setAcademicUnivPeriodFirst(newAcademicUnivPeriodFirstList[i]);
				academicUniv.setAcademicUnivPeriodFirstOption(newAcademicUnivPeriodFirstOptionList[i]);
				academicUniv.setAcademicUnivPeriodLast(newAcademicUnivPeriodLastList[i]);
				academicUniv.setAcademicUnivPeriodLastOption(newAcademicUnivPeriodLastOptionList[i]);
				academicUniv.setAcademicUnivDegree(newAcademicUnivDegreeList[i]);
				academicUniv.setAcademicUnivName(newAcademicUnivNameList[i]);
				academicUniv.setAcademicUnivMajor(newAcademicUnivMajorList[i]);
				academicUniv.setAcademicUnivScore(newAcademicUnivScoreList[i]);
				academicUniv.setAcademicUnivScoreOption(newAcademicUnivScoreOptionList[i]);
				academicUniv.setAcademicUnivDoubleName(newAcademicUnivDoubleNameList[i]);
				academicUniv.setAcademicUnivDoubleOption(newAcademicUnivDoubleOptionList[i]);
				this.resumeService.addAcademicUniv(academicUniv);
				System.out.println("test2");
			}
		}
				
		
		session.setAttribute("loadMsg", "save");
		return "redirect:/resume/academic";
	}
	
	// 학력사항, 대학교 삭제
	@RequestMapping(value="/academic/delUniv", method = RequestMethod.GET)
	public String delResumeAcademic(@RequestParam("academicUnivNo") int academicUnivNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");		
		AcademicUniv academicUniv = this.resumeService.getAcademicUniv(academicUnivNo);	
		if(academicUniv.getMemberId().equals(memberId))
			this.resumeService.delAcademicUniv(academicUnivNo);
	    
		
		session.setAttribute("loadMsg", "delete");
	    return "redirect:/resume/academic";
	}
	
	
	
	
	// OA 능력 불러오기
	@RequestMapping(value="/oa", method = RequestMethod.GET)
	public String getResumeOA(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		OA oa = this.resumeService.getOA(memberId);
	    model.addAttribute("oa", oa);     
	    createLoadMsg(model);
	    return "resume/resume-oa";
	}
	
	// OA 능력 저장하기.
	@RequestMapping(value="/oa", method = RequestMethod.POST)
	public String postResumeOA(	@ModelAttribute("oa") OA oa) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// OA 테이블 업데이트		
		oa.setMemberId(memberId);
		oa.setOaRegDate(new Timestamp(System.currentTimeMillis()));
		this.resumeService.modOA(oa);
		session.setAttribute("loadMsg", "save");
		
		return "resume/resume-oa";
	}
	
	
	
	// 강점/성격 불러오기.
	@RequestMapping(value="/strength", method = RequestMethod.GET)
	public String getResumeCharacter(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Profile profile = this.resumeService.getProfile(memberId);
	    model.addAttribute("profile", profile);	 
	    createLoadMsg(model);
	    return "resume/resume-strength";
	}
	
	
	// 자격증 면허증 불러오기.
	@RequestMapping(value="/license", method = RequestMethod.GET)
	public String getResumeLicense(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<License> licenseList = this.resumeService.getLicenseList(memberId);
	    model.addAttribute("licenseList", licenseList);
	    createLoadMsg(model);
	    return "resume/resume-license";
	}
	// 자격증 면허증 데이터 처리
	@RequestMapping(value="/license", method = RequestMethod.POST)
	public String postResumeLicense(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
				
		// 자격증 업데이트
		String[] licenseNoList = request.getParameterValues("licenseNo");
		String[] licenseDateList = request.getParameterValues("licenseDate");
		String[] licenseNameList = request.getParameterValues("licenseName");
		String[] licenseOrgList = request.getParameterValues("licenseOrg");
		String[] licensePassList = request.getParameterValues("licensePass");
		
		if(request.getParameterValues("licenseNo") != null)
		{
			for(int i=0; i<licenseNoList.length; i++)
			{
				License license = new License();
				license.setMemberId(memberId);
				license.setLicenseRegDate(new Timestamp(System.currentTimeMillis()));
				license.setLicenseNo(Integer.parseInt(licenseNoList[i]));
				license.setLicenseDate(licenseDateList[i]);
				license.setLicenseName(licenseNameList[i]);
				license.setLicenseOrg(licenseOrgList[i]);
				license.setLicensePass(licensePassList[i]);				
				this.resumeService.modLicense(license);
			}
		}
		
		// 자격증 항목 추가
		String[] newLicenseDateList = request.getParameterValues("newLicenseDate");
		String[] newLicenseNameList = request.getParameterValues("newLicenseName");
		String[] newLicenseOrgList = request.getParameterValues("newLicenseOrg");
		String[] newLicensePassList = request.getParameterValues("newLicensePass");
		
		
		if(request.getParameterValues("newLicenseDate") != null)
		{
			for(int i=0; i<newLicenseDateList.length; i++)
			{
				License license = new License();
				license.setMemberId(memberId);
				license.setLicenseRegDate(new Timestamp(System.currentTimeMillis()));
				license.setLicenseDate(newLicenseDateList[i]);
				license.setLicenseName(newLicenseNameList[i]);
				license.setLicenseOrg(newLicenseOrgList[i]);
				license.setLicensePass(newLicensePassList[i]);				
				this.resumeService.addLicense(license);				
			}
		}
				
		session.setAttribute("loadMsg", "save");
		
		return "redirect:/resume/license";
	}
	
	// 자격증/면허증 삭제
	@RequestMapping(value="/license/delLicense", method = RequestMethod.GET)
	public String delResumeLicense(@RequestParam("licenseNo") int licenseNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");		
		License license = this.resumeService.getLicense(licenseNo);
		
		if(license.getMemberId().equals(memberId))
			this.resumeService.delLicense(licenseNo);
		
		session.setAttribute("loadMsg", "delete");
	    
	    return "redirect:/resume/license";
	}
	
	
	// 어학능력 불러오기
	@RequestMapping(value="/lang-ability", method = RequestMethod.GET)
	public String getResumeLangAbility(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<LanguageAbility> languageAbilityList = this.resumeService.getLanguageAbilityList(memberId);
	    model.addAttribute("languageAbilityList", languageAbilityList);	 
	    createLoadMsg(model);
	    return "resume/resume-lang-ability";
	}
	
	// 어학능력 데이터처리
	@RequestMapping(value="/lang-ability", method = RequestMethod.POST)
	public String postResumeLangAbility(ModelMap model,  HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 어학능력 업데이트
		String[] langAbilityNoList = request.getParameterValues("langAbilityNo");
		String[] langAbilityNameList = request.getParameterValues("langAbilityName");
		String[] langAbilityConversationList = request.getParameterValues("langAbilityConversation");
		String[] langAbilityCompositionList = request.getParameterValues("langAbilityComposition");
		String[] langAbilityReadingList = request.getParameterValues("langAbilityReading");
		
		if(request.getParameterValues("langAbilityNo") != null)
		{
			for(int i=0; i<langAbilityNoList.length; i++)
			{
				LanguageAbility languageAbility = new LanguageAbility();
				languageAbility.setMemberId(memberId);
				languageAbility.setLangAbilityRegDate(new Timestamp(System.currentTimeMillis()));
				languageAbility.setLangAbilityNo(Integer.parseInt(langAbilityNoList[i]));
				languageAbility.setLangAbilityName(langAbilityNameList[i]);
				languageAbility.setLangAbilityConversation(langAbilityConversationList[i]);
				languageAbility.setLangAbilityReading(langAbilityReadingList[i]);
				languageAbility.setLangAbilityComposition(langAbilityCompositionList[i]);
					
				this.resumeService.modLanguageAbility(languageAbility);
			}
		}
		
		// 어학능력 항목 추가
		String[] newLangAbilityNameList = request.getParameterValues("newLangAbilityName");
		String[] newLangAbilityConversationList = request.getParameterValues("newLangAbilityConversation");
		String[] newLangAbilityCompositionList = request.getParameterValues("newLangAbilityComposition");
		String[] newLangAbilityReadingList = request.getParameterValues("newLangAbilityReading");
		
		if(request.getParameterValues("newLangAbilityName") != null)
		{
			for(int i=0; i<newLangAbilityNameList.length; i++)
			{
				LanguageAbility languageAbility = new LanguageAbility();
				languageAbility.setMemberId(memberId);
				languageAbility.setLangAbilityRegDate(new Timestamp(System.currentTimeMillis()));
				languageAbility.setLangAbilityName(newLangAbilityNameList[i]);
				languageAbility.setLangAbilityConversation(newLangAbilityConversationList[i]);
				languageAbility.setLangAbilityReading(newLangAbilityReadingList[i]);
				languageAbility.setLangAbilityComposition(newLangAbilityCompositionList[i]);			
				this.resumeService.addLanguageAbility(languageAbility);		
			}
		}
		session.setAttribute("loadMsg", "save");
		return "redirect:/resume/lang-ability";
	}
	
	// 어학능력 삭제
	@RequestMapping(value="/lang-ability/delLangAbility", method = RequestMethod.GET)
	public String delResumeLangAbility(@RequestParam("langAbilityNo") int langAbilityNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");		
		LanguageAbility languageAbility = this.resumeService.getLanguageAbility(langAbilityNo);
		if(languageAbility.getMemberId().equals(memberId))
			this.resumeService.delLanguageAbility(langAbilityNo);
	    
		session.setAttribute("loadMsg", "delete");
	    return "redirect:/resume/lang-ability";
	}
	
	//어학시험 불러오기
	@RequestMapping(value="/lang-exam", method = RequestMethod.GET)
	public String getResumeLangExam(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<LanguageExam> languageExamList = this.resumeService.getLanguageExamList(memberId);
	    model.addAttribute("languageExamList", languageExamList);	
	    createLoadMsg(model);
	    return "resume/resume-lang-exam";
	}
	
	//어학시험 데이터 처리
	@RequestMapping(value="/lang-exam", method = RequestMethod.POST)
	public String postResumeLangExam(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");

		// 어학시험 업데이트
		String[] langExamNoList = request.getParameterValues("langExamNo");
		String[] langExamLNameList = request.getParameterValues("langExamLName");
		String[] langExamENameList = request.getParameterValues("langExamEName");
		String[] langExamScoreList = request.getParameterValues("langExamScore");
		String[] langExamTypeList = request.getParameterValues("langExamType");
		String[] langExamOrgList = request.getParameterValues("langExamOrg");
		
		if(request.getParameterValues("langExamNo") != null)
		{
			for(int i=0; i<langExamNoList.length; i++)
			{
				LanguageExam languageExam = new LanguageExam();
				languageExam.setMemberId(memberId);
				languageExam.setLangExamNo(Integer.parseInt(langExamNoList[i]));
				languageExam.setLangExamRegDate(new Timestamp(System.currentTimeMillis()));
				languageExam.setLangExamLName(langExamLNameList[i]);
				languageExam.setLangExamEName(langExamENameList[i]);
				languageExam.setLangExamScore(langExamScoreList[i]);
				languageExam.setLangExamType(langExamTypeList[i]);
				languageExam.setLangExamOrg(langExamOrgList[i]);
					
				this.resumeService.modLanguageExam(languageExam);
			}
		}
		
		// 어학시험 항목 추가
		String[] newLangExamLNameList = request.getParameterValues("newLangExamLName");
		String[] newLangExamENameList = request.getParameterValues("newLangExamEName");
		String[] newLangExamScoreList = request.getParameterValues("newLangExamScore");
		String[] newLangExamTypeList = request.getParameterValues("newLangExamType");
		String[] newLangExamOrgList = request.getParameterValues("newLangExamOrg");
		
		if(request.getParameterValues("newLangExamLName") != null)
		{
			for(int i=0; i<newLangExamLNameList.length; i++)
			{
				LanguageExam languageExam = new LanguageExam();
				languageExam.setMemberId(memberId);
				languageExam.setLangExamRegDate(new Timestamp(System.currentTimeMillis()));
				languageExam.setLangExamLName(newLangExamLNameList[i]);
				languageExam.setLangExamEName(newLangExamENameList[i]);
				languageExam.setLangExamScore(newLangExamScoreList[i]);
				languageExam.setLangExamType(newLangExamTypeList[i]);
				languageExam.setLangExamOrg(newLangExamOrgList[i]);
					
				this.resumeService.addLanguageExam(languageExam);
			}
		}
		
		session.setAttribute("loadMsg", "save");
		
		return "redirect:/resume/lang-exam";
	}
	// 어학시험 삭제
	@RequestMapping(value="/lang-exam/delLangExam", method = RequestMethod.GET)
	public String delResumeLangExam(@RequestParam("langExamNo") int langExamNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");		
		LanguageExam languageExam = this.resumeService.getLanguageExam(langExamNo);
		if(languageExam.getMemberId().equals(memberId))
			this.resumeService.delLanguageExam(langExamNo);
	    
		session.setAttribute("loadMsg", "delete");
		
	    return "redirect:/resume/lang-exam";
	}
	
	
	//수상내역 불러오기
	@RequestMapping(value="/award", method = RequestMethod.GET)
	public String getResumeAward(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Award> awardList = this.resumeService.getAwardList(memberId);
	    model.addAttribute("awardList", awardList);	
	    createLoadMsg(model);
	    return "resume/resume-award";
	}
	// 수상내역 데이터처리
	@RequestMapping(value="/award", method = RequestMethod.POST)
	public String postResumeAward(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 수상내역 업데이트
		String[] awardNoList = request.getParameterValues("awardNo");
		String[] awardDateList = request.getParameterValues("awardDate");
		String[] awardNameList = request.getParameterValues("awardName");
		String[] awardOrgList = request.getParameterValues("awardOrg");
		String[] awardSectionList = request.getParameterValues("awardSection");
		
		if(request.getParameterValues("awardNo") != null)
		{
			for(int i=0; i<awardNoList.length; i++)
			{
				Award award = new Award();
				award.setMemberId(memberId);
				award.setAwardRegDate(new Timestamp(System.currentTimeMillis()));
				award.setAwardNo(Integer.parseInt(awardNoList[i]));
				award.setAwardDate(awardDateList[i]);
				award.setAwardName(awardNameList[i]);
				award.setAwardOrg(awardOrgList[i]);
				award.setAwardSection(awardSectionList[i]);
				
				this.resumeService.modAward(award);
			}
		}
		
		// 수상내역 항목 추가
		String[] newAwardDateList = request.getParameterValues("newAwardDate");
		String[] newAwardNameList = request.getParameterValues("newAwardName");
		String[] newAwardOrgList = request.getParameterValues("newAwardOrg");
		String[] newAwardSectionList = request.getParameterValues("newAwardSection");
		
		if(request.getParameterValues("newAwardDate") != null)
		{
			for(int i=0; i<newAwardDateList.length; i++)
			{
				Award award = new Award();
				award.setMemberId(memberId);
				award.setAwardRegDate(new Timestamp(System.currentTimeMillis()));
				award.setAwardDate(newAwardDateList[i]);
				award.setAwardName(newAwardNameList[i]);
				award.setAwardOrg(newAwardOrgList[i]);
				award.setAwardSection(newAwardSectionList[i]);
				
				this.resumeService.addAward(award);
			}
		}
		
		session.setAttribute("loadMsg", "save");
	    return "redirect:/resume/award";
	}
	
	// 수상내역 삭제
	@RequestMapping(value="/award/delAward", method = RequestMethod.GET)
	public String delResumeAward(@RequestParam("awardNo") int awardNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Award award = this.resumeService.getAward(awardNo);		
		if(award.getMemberId().equals(memberId))
			this.resumeService.delAward(awardNo);
			
		
		session.setAttribute("loadMsg", "delete");
	    return "redirect:/resume/award";
	}
	
	
	// 경력 및 실습사항 불러오기
	@RequestMapping(value="/career", method = RequestMethod.GET)
	public String getResumeCareer(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Career> careerList = this.resumeService.getCareerList(memberId);
	    model.addAttribute("careerList", careerList);
	    createLoadMsg(model);
	    return "resume/resume-career";
	}
	
	// 경력 및 실습사항  데이터 처리
	@RequestMapping(value="/career", method = RequestMethod.POST)
	public String postResumeCareer(ModelMap model,  HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 경력 및 실습사항  업데이트
		String[] careerNoList = request.getParameterValues("careerNo");
		String[] careerPeriodList = request.getParameterValues("careerPeriod");
		String[] careerCompanyList = request.getParameterValues("careerCompany");
		String[] careerPositionList = request.getParameterValues("careerPosition");
		String[] careerWorkList = request.getParameterValues("careerWork");
		String[] careerPostList = request.getParameterValues("careerPost");
	
		if(request.getParameterValues("careerNo") != null)
		{
			for(int i=0; i<careerNoList.length; i++)
			{
				Career career = new Career();
				career.setMemberId(memberId);
				career.setCareerRegDate(new Timestamp(System.currentTimeMillis()));
				career.setCareerNo(Integer.parseInt(careerNoList[i]));
				career.setCareerPeriod(careerPeriodList[i]);
				career.setCareerCompany(careerCompanyList[i]);
				career.setCareerPosition(careerPositionList[i]);
				career.setCareerWork(careerWorkList[i]);
				career.setCareerPost(careerPostList[i]);
								
				this.resumeService.modCareer(career);
			}
		}
		
		// 경력 및 실습사항  항목 추가
		String[] newCareerPeriodList = request.getParameterValues("newCareerPeriod");
		String[] newCareerCompanyList = request.getParameterValues("newCareerCompany");
		String[] newCareerPositionList = request.getParameterValues("newCareerPosition");
		String[] newCareerWorkList = request.getParameterValues("newCareerWork");
		String[] newCareerPostList = request.getParameterValues("newCareerPost");
		
		if(request.getParameterValues("newCareerPeriod") != null)
		{
			for(int i=0; i<newCareerPeriodList.length; i++)
			{
				Career career = new Career();
				career.setMemberId(memberId);
				career.setCareerRegDate(new Timestamp(System.currentTimeMillis()));
				career.setCareerPeriod(newCareerPeriodList[i]);
				career.setCareerCompany(newCareerCompanyList[i]);
				career.setCareerPosition(newCareerPositionList[i]);
				career.setCareerWork(newCareerWorkList[i]);
				career.setCareerPost(newCareerPostList[i]);
								
				this.resumeService.addCareer(career);
			}
		}
		
		session.setAttribute("loadMsg", "save");
		
		return "redirect:/resume/career";
	}
	
	// 경력 및 실습사항  삭제
	@RequestMapping(value="/career/delCareer", method = RequestMethod.GET)
	public String delResumeCareer(@RequestParam("careerNo") int careerNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Career career = this.resumeService.getCareer(careerNo);
		if(career.getMemberId().equals(memberId))
			this.resumeService.delCareer(careerNo);
			
		session.setAttribute("loadMsg", "delete");
		
	    return "redirect:/resume/career";
	}
	
	//봉사활동 불러오기
	@RequestMapping(value="/voluntary", method = RequestMethod.GET)
	public String getResumeVoluntary(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Voluntary> voluntaryList = this.resumeService.getVoluntaryList(memberId);
	    model.addAttribute("voluntaryList", voluntaryList);
	    createLoadMsg(model);
	    return "resume/resume-voluntary";
	}
	//봉사활동 데이터처리
	@RequestMapping(value="/voluntary", method = RequestMethod.POST)
	public String postResumeVoluntary(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 봉사활동  업데이트
		String[] voluntaryNoList = request.getParameterValues("voluntaryNo");
		String[] voluntaryPeriodList = request.getParameterValues("voluntaryPeriod");
		String[] voluntaryOrgList = request.getParameterValues("voluntaryOrg");
		String[] voluntaryContentList = request.getParameterValues("voluntaryContent");
	
		if(request.getParameterValues("voluntaryNo") != null)
		{
			for(int i=0; i<voluntaryNoList.length; i++)
			{
				Voluntary voluntary = new Voluntary();
				voluntary.setMemberId(memberId);
				voluntary.setVoluntaryNo(Integer.parseInt(voluntaryNoList[i]));
				voluntary.setVoluntaryRegDate(new Timestamp(System.currentTimeMillis()));
				voluntary.setVoluntaryPeriod(voluntaryPeriodList[i]);
				voluntary.setVoluntaryContent(voluntaryContentList[i]);
				voluntary.setVoluntaryOrg(voluntaryOrgList[i]);
								
				this.resumeService.modVoluntary(voluntary);
			}
		}
		
		// 봉사활동  항목 추가
		String[] newVoluntaryPeriodList = request.getParameterValues("newVoluntaryPeriod");
		String[] newVoluntaryOrgList = request.getParameterValues("newVoluntaryOrg");
		String[] newVoluntaryContentList = request.getParameterValues("newVoluntaryContent");
		
		if(request.getParameterValues("newVoluntaryPeriod") != null)
		{
			for(int i=0; i<newVoluntaryPeriodList.length; i++)
			{
				Voluntary voluntary = new Voluntary();
				voluntary.setMemberId(memberId);
				voluntary.setVoluntaryRegDate(new Timestamp(System.currentTimeMillis()));
				voluntary.setVoluntaryPeriod(newVoluntaryPeriodList[i]);
				voluntary.setVoluntaryContent(newVoluntaryContentList[i]);
				voluntary.setVoluntaryOrg(newVoluntaryOrgList[i]);
								
				this.resumeService.addVoluntary(voluntary);
			}
		}
		
		session.setAttribute("loadMsg", "save");
		
	    return "redirect:/resume/voluntary";
	}
	//봉사활동 항목 삭제
	@RequestMapping(value="/voluntary/delVoluntary", method = RequestMethod.GET)
	public String delResumeVoluntary(@RequestParam("voluntaryNo") int voluntaryNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Voluntary voluntary = this.resumeService.getVoluntary(voluntaryNo);
		if(voluntary.getMemberId().equals(memberId))
			this.resumeService.delVoluntary(voluntaryNo);
					   
		
		session.setAttribute("loadMsg", "delete");
	    return "redirect:/resume/voluntary";
	}
	
	// 교육이수 불러오기
	@RequestMapping(value="/education", method = RequestMethod.GET)
	  public String getResumeEducation(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Education> educationList = this.resumeService.getEducationList(memberId);
	    model.addAttribute("educationList", educationList);
	    createLoadMsg(model);
	    return "resume/resume-education";
	}
	// 교육이수 데이터처리
	@RequestMapping(value="/education", method = RequestMethod.POST)
	  public String postResumeEducation(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 교육이수  업데이트
		String[] educationNoList = request.getParameterValues("educationNo");
		String[] educationPeriodList = request.getParameterValues("educationPeriod");
		String[] educationNameList = request.getParameterValues("educationName");
		String[] educationOrgList = request.getParameterValues("educationOrg");
	
		if(request.getParameterValues("educationNo") != null)
		{
			for(int i=0; i<educationNoList.length; i++)
			{
				Education education = new Education();
				education.setMemberId(memberId);
				education.setEducationRegDate(new Timestamp(System.currentTimeMillis()));
				education.setEducationNo(Integer.parseInt(educationNoList[i]));	
				education.setEducationPeriod(educationPeriodList[i]);
				education.setEducationName(educationNameList[i]);
				education.setEducationOrg(educationOrgList[i]);
								
				this.resumeService.modEducation(education);
			}
		}
		
		// 교육이수  항목 추가
		String[] newEducationPeriodList = request.getParameterValues("newEducationPeriod");
		String[] newEducationNameList = request.getParameterValues("newEducationName");
		String[] newEducationOrgList = request.getParameterValues("newEducationOrg");
		
		if(request.getParameterValues("newEducationPeriod") != null)
		{
			for(int i=0; i<newEducationPeriodList.length; i++)
			{
				Education education = new Education();
				education.setMemberId(memberId);
				education.setEducationRegDate(new Timestamp(System.currentTimeMillis()));
				education.setEducationPeriod(newEducationPeriodList[i]);
				education.setEducationName(newEducationNameList[i]);
				education.setEducationOrg(newEducationOrgList[i]);
								
				this.resumeService.addEducation(education);
			}
		}
		
		
		session.setAttribute("loadMsg", "save");
	    return "redirect:/resume/education";
	}
	
	// 교육이수 항목 삭제
	@RequestMapping(value="/education/delEducation", method = RequestMethod.GET)
	  public String delResumeEducation(@RequestParam("educationNo") int educationNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Education education = this.resumeService.getEducation(educationNo);
		if(education.getMemberId().equals(memberId))
			this.resumeService.delEducation(educationNo);
		
		session.setAttribute("loadMsg", "delete");
		
	    return "redirect:/resume/education";
	}
	
	// 프로젝트경험 불러오기
	@RequestMapping(value="/project", method = RequestMethod.GET)
	  public String getResumeProject(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Project> projectList = this.resumeService.getProjectList(memberId);
	    model.addAttribute("projectList", projectList);
	    
	    createLoadMsg(model);
	    return "resume/resume-project";
	}
	// 프로젝트경험 데이터처리
	@RequestMapping(value="/project", method = RequestMethod.POST)
	  public String postResumeProject(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 프로젝트경험  업데이트
		String[] projectNoList = request.getParameterValues("projectNo");
		String[] projectPeriodList = request.getParameterValues("projectPeriod");
		String[] projectNameList = request.getParameterValues("projectName");
		String[] projectContextList = request.getParameterValues("projectContext");
		String[] projectSectionList = request.getParameterValues("projectSection");
	
		if(request.getParameterValues("projectNo") != null)
		{
			for(int i=0; i<projectNoList.length; i++)
			{
				Project project = new Project();
				project.setMemberId(memberId);
				project.setProjectRegDate(new Timestamp(System.currentTimeMillis()));
				project.setProjectNo(Integer.parseInt(projectNoList[i]));
				project.setProjectContext(projectContextList[i]);
				project.setProjectName(projectNameList[i]);
				project.setProjectPeriod(projectPeriodList[i]);
				project.setProjectSection(projectSectionList[i]);
								
				this.resumeService.modProject(project);
			}
		}
		
		// 프로젝트경험  항목 추가
		String[] newProjectPeriodList = request.getParameterValues("newProjectPeriod");
		String[] newProjectNameList = request.getParameterValues("newProjectName");
		String[] newProjectContextList = request.getParameterValues("newProjectContext");
		String[] newProjectSectionList = request.getParameterValues("newProjectSection");
		
		if(request.getParameterValues("newProjectPeriod") != null)
		{
			for(int i=0; i<newProjectPeriodList.length; i++)
			{
				Project project = new Project();
				project.setMemberId(memberId);
				project.setProjectRegDate(new Timestamp(System.currentTimeMillis()));
				project.setProjectContext(newProjectContextList[i]);
				project.setProjectName(newProjectNameList[i]);
				project.setProjectPeriod(newProjectPeriodList[i]);
				project.setProjectSection(newProjectSectionList[i]);
								
				this.resumeService.addProject(project);
			}
		}
		
		session.setAttribute("loadMsg", "save");
	    return "redirect:/resume/project";
	}
	// 프로젝트경험 항목 삭제
	@RequestMapping(value="/project/delProject", method = RequestMethod.GET)
	  public String delResumeProject(@RequestParam("projectNo") int projectNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Project project = this.resumeService.getProject(projectNo);
		if(project.getMemberId().equals(memberId))
			this.resumeService.delProject(projectNo);
		
		session.setAttribute("loadMsg", "delete");
	    return "redirect:/resume/project";
	}
	
	//저술내역 불러오기
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String getResumeWrite(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Write> writeList = this.resumeService.getWriteList(memberId);
	    model.addAttribute("writeList", writeList);
	    
	    createLoadMsg(model);
	    return "resume/resume-write";
	}
	//저술내역 데이터처리
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String postResumeWrite(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 저술내역  업데이트
		String[] writeNoList = request.getParameterValues("writeNo");
		String[] writeDateList = request.getParameterValues("writeDate");
		String[] writeSectionList = request.getParameterValues("writeSection");
		String[] writeNameList = request.getParameterValues("writeName");
		String[] writeOrgList = request.getParameterValues("writeOrg");
	
		if(request.getParameterValues("writeNo") != null)
		{
			for(int i=0; i<writeNoList.length; i++)
			{
				Write write = new Write();
				write.setMemberId(memberId);
				write.setWriteRegDate(new Timestamp(System.currentTimeMillis()));
				write.setWriteNo(Integer.parseInt(writeNoList[i]));
				write.setWriteDate(writeDateList[i]);
				write.setWriteName(writeNameList[i]);
				write.setWriteSection(writeSectionList[i]);
				write.setWriteOrg(writeOrgList[i]);
								
				this.resumeService.modWrite(write);
			}
		}
		
		// 저술내역  항목 추가
		String[] newWriteDateList = request.getParameterValues("newWriteDate");
		String[] newWriteSectionList = request.getParameterValues("newWriteSection");
		String[] newWriteNameList = request.getParameterValues("newWriteName");
		String[] newWriteOrgList = request.getParameterValues("newWriteOrg");
		
		if(request.getParameterValues("newWriteDate") != null)
		{
			for(int i=0; i<newWriteDateList.length; i++)
			{
				Write write = new Write();
				write.setMemberId(memberId);
				write.setWriteRegDate(new Timestamp(System.currentTimeMillis()));
				write.setWriteDate(newWriteDateList[i]);
				write.setWriteName(newWriteNameList[i]);
				write.setWriteSection(newWriteSectionList[i]);
				write.setWriteOrg(newWriteOrgList[i]);
								
				this.resumeService.addWrite(write);
			}
		}
		session.setAttribute("loadMsg", "save");
		
	    return "redirect:/resume/write";
	}
	//저술내역 항목삭제
	@RequestMapping(value="/write/delWrite", method = RequestMethod.GET)
	public String getResumeWrite(@RequestParam("writeNo") int writeNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Write write = this.resumeService.getWrite(writeNo);
		if(write.getMemberId().equals(memberId))
			this.resumeService.delWrite(writeNo);
		
		session.setAttribute("loadMsg", "delete");
		
	    return "redirect:/resume/write";
	}
	
	//글로벌 경험 불러오기
	@RequestMapping(value="/global", method = RequestMethod.GET)
	public String getResumeGlobal(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		List<Global> globalList = this.resumeService.getGlobalList(memberId);
	    model.addAttribute("globalList", globalList);
	    
	    createLoadMsg(model);
	    return "resume/resume-global";
	}
	//글로벌경험 데이터처리
	@RequestMapping(value="/global", method = RequestMethod.POST)
	public String postResumeGlobal(ModelMap model, HttpServletRequest request) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
		// 글로벌경험  업데이트
		String[] globalNoList = request.getParameterValues("globalNo");
		String[] globalPeriodList = request.getParameterValues("globalPeriod");
		String[] globalNationList = request.getParameterValues("globalNation");
		String[] globalPurposeList = request.getParameterValues("globalPurpose");
		String[] globalContentList = request.getParameterValues("globalContent");
	
		if(request.getParameterValues("globalNo") != null)
		{
			for(int i=0; i<globalNoList.length; i++)
			{
				Global global = new Global();
				global.setMemberId(memberId);
				global.setGlobalRegDate(new Timestamp(System.currentTimeMillis()));
				global.setGlobalNo(Integer.parseInt(globalNoList[i]));
				global.setGlobalPeriod(globalPeriodList[i]);
				global.setGlobalNation(globalNationList[i]);
				global.setGlobalPurpose(globalPurposeList[i]);
				global.setGlobalContent(globalContentList[i]);
								
				this.resumeService.modGlobal(global);
			}
		}
		
		// 글로벌경험  항목 추가
		String[] newGlobalPeriodList = request.getParameterValues("newGlobalPeriod");
		String[] newGlobalNationList = request.getParameterValues("newGlobalNation");
		String[] newGlobalPurposeList = request.getParameterValues("newGlobalPurpose");
		String[] newGlobalContentList = request.getParameterValues("newGlobalContent");
		
		if(request.getParameterValues("newGlobalPeriod") != null)
		{
			for(int i=0; i<newGlobalPeriodList.length; i++)
			{
				Global global = new Global();
				global.setMemberId(memberId);
				global.setGlobalRegDate(new Timestamp(System.currentTimeMillis()));
				global.setGlobalPeriod(newGlobalPeriodList[i]);
				global.setGlobalNation(newGlobalNationList[i]);
				global.setGlobalPurpose(newGlobalPurposeList[i]);
				global.setGlobalContent(newGlobalContentList[i]);
								
				this.resumeService.addGlobal(global);
			}
		}
		
		session.setAttribute("loadMsg", "save");
		
	    return "redirect:/resume/global";
	}
	//글로벌경험 항목삭제
	@RequestMapping(value="/global/delGlobal", method = RequestMethod.GET)
	public String delResumeGlobal(@RequestParam("globalNo") int globalNo, ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Global global = this.resumeService.getGlobal(globalNo);
		if(global.getMemberId().equals(memberId))
			this.resumeService.delGlobal(globalNo);
		
		session.setAttribute("loadMsg", "delete");
		
	    return "redirect:/resume/global";
	}
	
	//SWOT분석 불러오기
	@RequestMapping(value="/swot", method = RequestMethod.GET)
	public String getResumeSwot(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Swot swot = this.resumeService.getSwot(memberId);
	    model.addAttribute("swot", swot);
	    createLoadMsg(model);
	    return "resume/resume-swot";
	}
	
	// SWOT 데이터 처리
	@RequestMapping(value ="/swot", method = RequestMethod.POST)
    public String postResumeSwot(@ModelAttribute("swot") Swot swot) {  	    	
		String memberId = (String)session.getAttribute("memberLoginInfo");
		
    	// PROFILE 테이블 업데이트
		swot.setMemberId(memberId);
		swot.setSwotRegDate(new Timestamp(System.currentTimeMillis()));
        this.resumeService.modSwot(swot);
                
        session.setAttribute("loadMsg", "save");
        return "redirect:/resume/swot";
    }
	
	@RequestMapping(value="/config", method = RequestMethod.GET)
	public String getResumeConfig(ModelMap model) {
		String memberId = (String)session.getAttribute("memberLoginInfo");
		Profile profile = this.resumeService.getProfile(memberId);
	    model.addAttribute("profile", profile);
	    return "resume/resume-config";
	}
	
	
	
	// 로딩 메시지 구성
	private void createLoadMsg(ModelMap model)
	{
		String loadMsg = (String) session.getAttribute("loadMsg");
	    if(loadMsg!=null)
	    {
	    	model.addAttribute("loadMsg", loadMsg);	    	
	    	session.removeAttribute("loadMsg");
	    }
	}
	
}