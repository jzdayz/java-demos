package io.github.jzdayz.database.jdbc.hibernate;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author MBP
 * @since 2020-12-20
 */
@TableName("cd_view_sale_contract_list_all_expand")
public class CdViewSaleContractListAllExpand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("SALE_CONTRACT_ID")
    private String saleContractId;

    @TableField("SEAL_COMPANY")
    private String sealCompany;

    @TableField("CENTER_NAME")
    private String centerName;

    @TableField("GROUP_Name")
    private String groupName;

    @TableField("DEPT_NAME")
    private String deptName;

    @TableField("SALE_CONTRACT_CODE")
    private String saleContractCode;

    @TableField("SALE_CONTRACT_NAME")
    private String saleContractName;

    @TableField("CUSTOMER_NAME")
    private String customerName;

    @TableField("SALE_NAME")
    private String saleName;

    @TableField("PM_NAME")
    private String pmName;

    @TableField("AUDIT_DATE")
    private String auditDate;

    @TableField("STAMP_DATE")
    private String stampDate;

    @TableField("SIGN_IN_DATE")
    private String signInDate;

    @TableField("FILE_DATE")
    private String fileDate;

    @TableField("SIGN_DATE")
    private String signDate;

    @TableField("FLOW_STATUS")
    private String flowStatus;

    @TableField("LAST_GP")
    private String lastGp;

    @TableField("CONTRACT_MONEY")
    private String contractMoney;

    @TableField("TOTAL_INVOICE")
    private String totalInvoice;

    @TableField("TOTAL_TOFUND")
    private String totalTofund;

    @TableField("PLAN_PURCHASE_MONEY")
    private String planPurchaseMoney;

    @TableField("FACT_PURCHASE_MONEY")
    private String factPurchaseMoney;

    @TableField("TOTAL_PAYMENT")
    private String totalPayment;

    @TableField("PAYMENT_2019")
    private String payment2019;

    @TableField("STOP_FLAG")
    private String stopFlag;

    @TableField("STATUS_LABEL")
    private String statusLabel;

    @TableField("ICT_SERVICE_SALE")
    private String ictServiceSale;

    @TableField("ICT_SERVICE_COST")
    private String ictServiceCost;

    @TableField("SOFT_DEV_SALE")
    private String softDevSale;

    @TableField("SOFT_DEV_COST")
    private String softDevCost;

    @TableField("SOFT_MAINTAIN_SALE")
    private String softMaintainSale;

    @TableField("SOFT_MAINTAIN_COST")
    private String softMaintainCost;

    @TableField("SYS_MAINTAIN_SALE")
    private String sysMaintainSale;

    @TableField("SYS_MAINTAIN_COST")
    private String sysMaintainCost;

    @TableField("OPERATION_SERVICE_SALE")
    private String operationServiceSale;

    @TableField("OPERATION_SERVICE_COST")
    private String operationServiceCost;

    @TableField("SOFT_HARDWARE_SALE")
    private String softHardwareSale;

    @TableField("SOFT_HARDWARE_COST")
    private String softHardwareCost;

    @TableField("ORIGINAL_SERVICE_SALE")
    private String originalServiceSale;

    @TableField("ORIGINAL_SERVICE_COST")
    private String originalServiceCost;

    @TableField("THIRD_PARTY_SALE")
    private String thirdPartySale;

    @TableField("THIRD_PARTY_COST")
    private String thirdPartyCost;

    @TableField("ENG_OUT_SALE")
    private String engOutSale;

    @TableField("ENG_OUT_COST")
    private String engOutCost;

    @TableField("SOFT_OUT_SALE")
    private String softOutSale;

    @TableField("SOFT_OUT_COST")
    private String softOutCost;

    @TableField("CONSULT_OUT_SALE")
    private String consultOutSale;

    @TableField("CONSULT_OUT_COST")
    private String consultOutCost;

    @TableField("PROJECT_SALE")
    private String projectSale;

    @TableField("PROJECT_COST")
    private String projectCost;

    @TableField("BID_SERVICE_SALE")
    private String bidServiceSale;

    @TableField("BID_SERVICE_COST")
    private String bidServiceCost;

    @TableField("IF_ALL_TOFUND")
    private String ifAllTofund;

    @TableField("TOTAL_INVOICE_END2019")
    private String totalInvoiceEnd2019;

    @TableField("CREATE_DATE")
    private String createDate;

    /**
     * id
     */
    @TableField("cs_id")
    private String csId;

    /**
     * CRM中销售合同id
     */
    @TableField("cs_crm_sale_contact_id")
    private String csCrmSaleContactId;

    @TableField("cs_crm_business_opportunity_id")
    private String csCrmBusinessOpportunityId;

    @TableField("cs_crm_project_id")
    private String csCrmProjectId;

    @TableField("cs_crm_project_name")
    private String csCrmProjectName;

    @TableField("cs_crm_project_no")
    private String csCrmProjectNo;

    @TableField("cs_dept_name")
    private String csDeptName;

    @TableField("cs_second_dept_code")
    private String csSecondDeptCode;

    @TableField("cs_second_dept_name")
    private String csSecondDeptName;

    @TableField("cs_first_parent_dept_code")
    private String csFirstParentDeptCode;

    @TableField("cs_first_parent_dept_name")
    private String csFirstParentDeptName;

    @TableField("cs_crm_sale_contact_no")
    private String csCrmSaleContactNo;

    @TableField("cs_crm_sale_contact_name")
    private String csCrmSaleContactName;

    @TableField("cs_crm_sale_dept_id")
    private String csCrmSaleDeptId;

    @TableField("cs_crm_sale_dept_name")
    private String csCrmSaleDeptName;

    @TableField("cs_crm_sale_company")
    private String csCrmSaleCompany;

    /**
     * 用印公司
     */
    @TableField("cs_crm_sale_company_name")
    private String csCrmSaleCompanyName;

    @TableField("cs_crm_pm_code")
    private String csCrmPmCode;

    @TableField("cs_crm_pm_name")
    private String csCrmPmName;

    @TableField("cs_im_code")
    private String csImCode;

    @TableField("cs_st_im_name")
    private String csStImName;

    @TableField("cs_st_salw_code")
    private String csStSalwCode;

    @TableField("cs_st_sale_name")
    private String csStSaleName;

    @TableField("cs_crm_sale_status")
    private String csCrmSaleStatus;

    /**
     * 逻辑删除标识。0-未删除，1-删除
     */
    @TableField("cs_is_delete")
    private String csIsDelete;

    /**
     * 创建时间
     */
    @TableField("cs_crea")
    private Date csCrea;

    /**
     * 合同类型
     */
    @TableField("cs_contact_type")
    private String csContactType;


    public String getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(String saleContractId) {
        this.saleContractId = saleContractId;
    }

    public String getSealCompany() {
        return sealCompany;
    }

    public void setSealCompany(String sealCompany) {
        this.sealCompany = sealCompany;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSaleContractCode() {
        return saleContractCode;
    }

    public void setSaleContractCode(String saleContractCode) {
        this.saleContractCode = saleContractCode;
    }

    public String getSaleContractName() {
        return saleContractName;
    }

    public void setSaleContractName(String saleContractName) {
        this.saleContractName = saleContractName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getStampDate() {
        return stampDate;
    }

    public void setStampDate(String stampDate) {
        this.stampDate = stampDate;
    }

    public String getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(String signInDate) {
        this.signInDate = signInDate;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getLastGp() {
        return lastGp;
    }

    public void setLastGp(String lastGp) {
        this.lastGp = lastGp;
    }

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(String totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    public String getTotalTofund() {
        return totalTofund;
    }

    public void setTotalTofund(String totalTofund) {
        this.totalTofund = totalTofund;
    }

    public String getPlanPurchaseMoney() {
        return planPurchaseMoney;
    }

    public void setPlanPurchaseMoney(String planPurchaseMoney) {
        this.planPurchaseMoney = planPurchaseMoney;
    }

    public String getFactPurchaseMoney() {
        return factPurchaseMoney;
    }

    public void setFactPurchaseMoney(String factPurchaseMoney) {
        this.factPurchaseMoney = factPurchaseMoney;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPayment2019() {
        return payment2019;
    }

    public void setPayment2019(String payment2019) {
        this.payment2019 = payment2019;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public String getIctServiceSale() {
        return ictServiceSale;
    }

    public void setIctServiceSale(String ictServiceSale) {
        this.ictServiceSale = ictServiceSale;
    }

    public String getIctServiceCost() {
        return ictServiceCost;
    }

    public void setIctServiceCost(String ictServiceCost) {
        this.ictServiceCost = ictServiceCost;
    }

    public String getSoftDevSale() {
        return softDevSale;
    }

    public void setSoftDevSale(String softDevSale) {
        this.softDevSale = softDevSale;
    }

    public String getSoftDevCost() {
        return softDevCost;
    }

    public void setSoftDevCost(String softDevCost) {
        this.softDevCost = softDevCost;
    }

    public String getSoftMaintainSale() {
        return softMaintainSale;
    }

    public void setSoftMaintainSale(String softMaintainSale) {
        this.softMaintainSale = softMaintainSale;
    }

    public String getSoftMaintainCost() {
        return softMaintainCost;
    }

    public void setSoftMaintainCost(String softMaintainCost) {
        this.softMaintainCost = softMaintainCost;
    }

    public String getSysMaintainSale() {
        return sysMaintainSale;
    }

    public void setSysMaintainSale(String sysMaintainSale) {
        this.sysMaintainSale = sysMaintainSale;
    }

    public String getSysMaintainCost() {
        return sysMaintainCost;
    }

    public void setSysMaintainCost(String sysMaintainCost) {
        this.sysMaintainCost = sysMaintainCost;
    }

    public String getOperationServiceSale() {
        return operationServiceSale;
    }

    public void setOperationServiceSale(String operationServiceSale) {
        this.operationServiceSale = operationServiceSale;
    }

    public String getOperationServiceCost() {
        return operationServiceCost;
    }

    public void setOperationServiceCost(String operationServiceCost) {
        this.operationServiceCost = operationServiceCost;
    }

    public String getSoftHardwareSale() {
        return softHardwareSale;
    }

    public void setSoftHardwareSale(String softHardwareSale) {
        this.softHardwareSale = softHardwareSale;
    }

    public String getSoftHardwareCost() {
        return softHardwareCost;
    }

    public void setSoftHardwareCost(String softHardwareCost) {
        this.softHardwareCost = softHardwareCost;
    }

    public String getOriginalServiceSale() {
        return originalServiceSale;
    }

    public void setOriginalServiceSale(String originalServiceSale) {
        this.originalServiceSale = originalServiceSale;
    }

    public String getOriginalServiceCost() {
        return originalServiceCost;
    }

    public void setOriginalServiceCost(String originalServiceCost) {
        this.originalServiceCost = originalServiceCost;
    }

    public String getThirdPartySale() {
        return thirdPartySale;
    }

    public void setThirdPartySale(String thirdPartySale) {
        this.thirdPartySale = thirdPartySale;
    }

    public String getThirdPartyCost() {
        return thirdPartyCost;
    }

    public void setThirdPartyCost(String thirdPartyCost) {
        this.thirdPartyCost = thirdPartyCost;
    }

    public String getEngOutSale() {
        return engOutSale;
    }

    public void setEngOutSale(String engOutSale) {
        this.engOutSale = engOutSale;
    }

    public String getEngOutCost() {
        return engOutCost;
    }

    public void setEngOutCost(String engOutCost) {
        this.engOutCost = engOutCost;
    }

    public String getSoftOutSale() {
        return softOutSale;
    }

    public void setSoftOutSale(String softOutSale) {
        this.softOutSale = softOutSale;
    }

    public String getSoftOutCost() {
        return softOutCost;
    }

    public void setSoftOutCost(String softOutCost) {
        this.softOutCost = softOutCost;
    }

    public String getConsultOutSale() {
        return consultOutSale;
    }

    public void setConsultOutSale(String consultOutSale) {
        this.consultOutSale = consultOutSale;
    }

    public String getConsultOutCost() {
        return consultOutCost;
    }

    public void setConsultOutCost(String consultOutCost) {
        this.consultOutCost = consultOutCost;
    }

    public String getProjectSale() {
        return projectSale;
    }

    public void setProjectSale(String projectSale) {
        this.projectSale = projectSale;
    }

    public String getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(String projectCost) {
        this.projectCost = projectCost;
    }

    public String getBidServiceSale() {
        return bidServiceSale;
    }

    public void setBidServiceSale(String bidServiceSale) {
        this.bidServiceSale = bidServiceSale;
    }

    public String getBidServiceCost() {
        return bidServiceCost;
    }

    public void setBidServiceCost(String bidServiceCost) {
        this.bidServiceCost = bidServiceCost;
    }

    public String getIfAllTofund() {
        return ifAllTofund;
    }

    public void setIfAllTofund(String ifAllTofund) {
        this.ifAllTofund = ifAllTofund;
    }

    public String getTotalInvoiceEnd2019() {
        return totalInvoiceEnd2019;
    }

    public void setTotalInvoiceEnd2019(String totalInvoiceEnd2019) {
        this.totalInvoiceEnd2019 = totalInvoiceEnd2019;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getCsCrmSaleContactId() {
        return csCrmSaleContactId;
    }

    public void setCsCrmSaleContactId(String csCrmSaleContactId) {
        this.csCrmSaleContactId = csCrmSaleContactId;
    }

    public String getCsCrmBusinessOpportunityId() {
        return csCrmBusinessOpportunityId;
    }

    public void setCsCrmBusinessOpportunityId(String csCrmBusinessOpportunityId) {
        this.csCrmBusinessOpportunityId = csCrmBusinessOpportunityId;
    }

    public String getCsCrmProjectId() {
        return csCrmProjectId;
    }

    public void setCsCrmProjectId(String csCrmProjectId) {
        this.csCrmProjectId = csCrmProjectId;
    }

    public String getCsCrmProjectName() {
        return csCrmProjectName;
    }

    public void setCsCrmProjectName(String csCrmProjectName) {
        this.csCrmProjectName = csCrmProjectName;
    }

    public String getCsCrmProjectNo() {
        return csCrmProjectNo;
    }

    public void setCsCrmProjectNo(String csCrmProjectNo) {
        this.csCrmProjectNo = csCrmProjectNo;
    }

    public String getCsDeptName() {
        return csDeptName;
    }

    public void setCsDeptName(String csDeptName) {
        this.csDeptName = csDeptName;
    }

    public String getCsSecondDeptCode() {
        return csSecondDeptCode;
    }

    public void setCsSecondDeptCode(String csSecondDeptCode) {
        this.csSecondDeptCode = csSecondDeptCode;
    }

    public String getCsSecondDeptName() {
        return csSecondDeptName;
    }

    public void setCsSecondDeptName(String csSecondDeptName) {
        this.csSecondDeptName = csSecondDeptName;
    }

    public String getCsFirstParentDeptCode() {
        return csFirstParentDeptCode;
    }

    public void setCsFirstParentDeptCode(String csFirstParentDeptCode) {
        this.csFirstParentDeptCode = csFirstParentDeptCode;
    }

    public String getCsFirstParentDeptName() {
        return csFirstParentDeptName;
    }

    public void setCsFirstParentDeptName(String csFirstParentDeptName) {
        this.csFirstParentDeptName = csFirstParentDeptName;
    }

    public String getCsCrmSaleContactNo() {
        return csCrmSaleContactNo;
    }

    public void setCsCrmSaleContactNo(String csCrmSaleContactNo) {
        this.csCrmSaleContactNo = csCrmSaleContactNo;
    }

    public String getCsCrmSaleContactName() {
        return csCrmSaleContactName;
    }

    public void setCsCrmSaleContactName(String csCrmSaleContactName) {
        this.csCrmSaleContactName = csCrmSaleContactName;
    }

    public String getCsCrmSaleDeptId() {
        return csCrmSaleDeptId;
    }

    public void setCsCrmSaleDeptId(String csCrmSaleDeptId) {
        this.csCrmSaleDeptId = csCrmSaleDeptId;
    }

    public String getCsCrmSaleDeptName() {
        return csCrmSaleDeptName;
    }

    public void setCsCrmSaleDeptName(String csCrmSaleDeptName) {
        this.csCrmSaleDeptName = csCrmSaleDeptName;
    }

    public String getCsCrmSaleCompany() {
        return csCrmSaleCompany;
    }

    public void setCsCrmSaleCompany(String csCrmSaleCompany) {
        this.csCrmSaleCompany = csCrmSaleCompany;
    }

    public String getCsCrmSaleCompanyName() {
        return csCrmSaleCompanyName;
    }

    public void setCsCrmSaleCompanyName(String csCrmSaleCompanyName) {
        this.csCrmSaleCompanyName = csCrmSaleCompanyName;
    }

    public String getCsCrmPmCode() {
        return csCrmPmCode;
    }

    public void setCsCrmPmCode(String csCrmPmCode) {
        this.csCrmPmCode = csCrmPmCode;
    }

    public String getCsCrmPmName() {
        return csCrmPmName;
    }

    public void setCsCrmPmName(String csCrmPmName) {
        this.csCrmPmName = csCrmPmName;
    }

    public String getCsImCode() {
        return csImCode;
    }

    public void setCsImCode(String csImCode) {
        this.csImCode = csImCode;
    }

    public String getCsStImName() {
        return csStImName;
    }

    public void setCsStImName(String csStImName) {
        this.csStImName = csStImName;
    }

    public String getCsStSalwCode() {
        return csStSalwCode;
    }

    public void setCsStSalwCode(String csStSalwCode) {
        this.csStSalwCode = csStSalwCode;
    }

    public String getCsStSaleName() {
        return csStSaleName;
    }

    public void setCsStSaleName(String csStSaleName) {
        this.csStSaleName = csStSaleName;
    }

    public String getCsCrmSaleStatus() {
        return csCrmSaleStatus;
    }

    public void setCsCrmSaleStatus(String csCrmSaleStatus) {
        this.csCrmSaleStatus = csCrmSaleStatus;
    }

    public String getCsIsDelete() {
        return csIsDelete;
    }

    public void setCsIsDelete(String csIsDelete) {
        this.csIsDelete = csIsDelete;
    }

    public Date getCsCrea() {
        return csCrea;
    }

    public void setCsCrea(Date csCrea) {
        this.csCrea = csCrea;
    }

    public String getCsContactType() {
        return csContactType;
    }

    public void setCsContactType(String csContactType) {
        this.csContactType = csContactType;
    }

    @Override
    public String toString() {
        return "CdViewSaleContractListAllExpand{" +
        "saleContractId=" + saleContractId +
        ", sealCompany=" + sealCompany +
        ", centerName=" + centerName +
        ", groupName=" + groupName +
        ", deptName=" + deptName +
        ", saleContractCode=" + saleContractCode +
        ", saleContractName=" + saleContractName +
        ", customerName=" + customerName +
        ", saleName=" + saleName +
        ", pmName=" + pmName +
        ", auditDate=" + auditDate +
        ", stampDate=" + stampDate +
        ", signInDate=" + signInDate +
        ", fileDate=" + fileDate +
        ", signDate=" + signDate +
        ", flowStatus=" + flowStatus +
        ", lastGp=" + lastGp +
        ", contractMoney=" + contractMoney +
        ", totalInvoice=" + totalInvoice +
        ", totalTofund=" + totalTofund +
        ", planPurchaseMoney=" + planPurchaseMoney +
        ", factPurchaseMoney=" + factPurchaseMoney +
        ", totalPayment=" + totalPayment +
        ", payment2019=" + payment2019 +
        ", stopFlag=" + stopFlag +
        ", statusLabel=" + statusLabel +
        ", ictServiceSale=" + ictServiceSale +
        ", ictServiceCost=" + ictServiceCost +
        ", softDevSale=" + softDevSale +
        ", softDevCost=" + softDevCost +
        ", softMaintainSale=" + softMaintainSale +
        ", softMaintainCost=" + softMaintainCost +
        ", sysMaintainSale=" + sysMaintainSale +
        ", sysMaintainCost=" + sysMaintainCost +
        ", operationServiceSale=" + operationServiceSale +
        ", operationServiceCost=" + operationServiceCost +
        ", softHardwareSale=" + softHardwareSale +
        ", softHardwareCost=" + softHardwareCost +
        ", originalServiceSale=" + originalServiceSale +
        ", originalServiceCost=" + originalServiceCost +
        ", thirdPartySale=" + thirdPartySale +
        ", thirdPartyCost=" + thirdPartyCost +
        ", engOutSale=" + engOutSale +
        ", engOutCost=" + engOutCost +
        ", softOutSale=" + softOutSale +
        ", softOutCost=" + softOutCost +
        ", consultOutSale=" + consultOutSale +
        ", consultOutCost=" + consultOutCost +
        ", projectSale=" + projectSale +
        ", projectCost=" + projectCost +
        ", bidServiceSale=" + bidServiceSale +
        ", bidServiceCost=" + bidServiceCost +
        ", ifAllTofund=" + ifAllTofund +
        ", totalInvoiceEnd2019=" + totalInvoiceEnd2019 +
        ", createDate=" + createDate +
        ", csId=" + csId +
        ", csCrmSaleContactId=" + csCrmSaleContactId +
        ", csCrmBusinessOpportunityId=" + csCrmBusinessOpportunityId +
        ", csCrmProjectId=" + csCrmProjectId +
        ", csCrmProjectName=" + csCrmProjectName +
        ", csCrmProjectNo=" + csCrmProjectNo +
        ", csDeptName=" + csDeptName +
        ", csSecondDeptCode=" + csSecondDeptCode +
        ", csSecondDeptName=" + csSecondDeptName +
        ", csFirstParentDeptCode=" + csFirstParentDeptCode +
        ", csFirstParentDeptName=" + csFirstParentDeptName +
        ", csCrmSaleContactNo=" + csCrmSaleContactNo +
        ", csCrmSaleContactName=" + csCrmSaleContactName +
        ", csCrmSaleDeptId=" + csCrmSaleDeptId +
        ", csCrmSaleDeptName=" + csCrmSaleDeptName +
        ", csCrmSaleCompany=" + csCrmSaleCompany +
        ", csCrmSaleCompanyName=" + csCrmSaleCompanyName +
        ", csCrmPmCode=" + csCrmPmCode +
        ", csCrmPmName=" + csCrmPmName +
        ", csImCode=" + csImCode +
        ", csStImName=" + csStImName +
        ", csStSalwCode=" + csStSalwCode +
        ", csStSaleName=" + csStSaleName +
        ", csCrmSaleStatus=" + csCrmSaleStatus +
        ", csIsDelete=" + csIsDelete +
        ", csCrea=" + csCrea +
        ", csContactType=" + csContactType +
        "}";
    }
}
