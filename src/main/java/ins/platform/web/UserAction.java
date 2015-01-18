package ins.platform.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.map.schema.model.PrpAreaInfo;
import ins.map.service.facade.PrpAreaInfoService;
import ins.platform.common.web.SinoMapBaseAction;
import ins.platform.schema.model.PrpDuser;
import ins.platform.schema.model.PrpRole;
import ins.platform.service.facade.PrpRoleService;
import ins.platform.service.facade.UserService;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.token.Sha512DigestUtils;

public class UserAction extends SinoMapBaseAction {
	private UserService userService;
	private PrpRoleService prpRoleService;
	private PrpAreaInfoService prpAreaInfoService;
	private PrpDuser prpDuser;
	private String userCode;
	private String newPassword;
	private String password;
	private List<PrpDuser> userList;
	private List<PrpRole> roleList;
	private List<PrpAreaInfo> areaInfoList;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public PrpAreaInfoService getPrpAreaInfoService() {
		return prpAreaInfoService;
	}

	public void setPrpAreaInfoService(PrpAreaInfoService prpAreaInfoService) {
		this.prpAreaInfoService = prpAreaInfoService;
	}

	public List<PrpAreaInfo> getAreaInfoList() {
		return areaInfoList;
	}

	public void setAreaInfoList(List<PrpAreaInfo> areaInfoList) {
		this.areaInfoList = areaInfoList;
	}

	public PrpRoleService getPrpRoleService() {
		return prpRoleService;
	}

	public void setPrpRoleService(PrpRoleService prpRoleService) {
		this.prpRoleService = prpRoleService;
	}

	public List<PrpRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<PrpRole> roleList) {
		this.roleList = roleList;
	}

	public List<PrpDuser> getUserList() {
		return userList;
	}

	public void setUserList(List<PrpDuser> userList) {
		this.userList = userList;
	}

	private int totalCount;
	
	public String getNewPassword() {
		return this.newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public UserService getUserService() {
		return this.userService;
	}

	public PrpDuser getPrpDuser() {
		return this.prpDuser;
	}

	public void setPrpDuser(PrpDuser prpDuser) {
		this.prpDuser = prpDuser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String query() {
		QueryRule queryRule = QueryRule.getInstance();
		if (this.prpDuser != null && (this.prpDuser.getUserCode() != null)
				&& (!("".equals(this.prpDuser.getUserCode())))) {
			queryRule.addEqual("userCode", this.prpDuser.getUserCode());
		}
		if (this.prpDuser != null && (this.prpDuser.getUserName() != null)
				&& (!("".equals(this.prpDuser.getUserName())))) {
			queryRule.addLike("userName", this.prpDuser.getUserName());
		}
		if (this.prpDuser != null && (this.prpDuser.getPrpAreaInfo() != null) &&
				(this.prpDuser.getPrpAreaInfo().getComCode() != null)	&& 
				(!("".equals(this.prpDuser.getPrpAreaInfo().getComCode())))) {
			queryRule.addEqual("prpAreaInfo.comCode", this.prpDuser.getPrpAreaInfo().getComCode());
		}
		Page page = this.userService.findUser(queryRule, this.pageNo,
				this.pageSize);
		this.userList = page.getResult();
		this.totalCount = (int) page.getTotalCount();
		return "success";
	}

	public String update() {
		
		try {
			this.userService.update(this.prpDuser);
			renderJSON(success(getText("action.editSuccess"),"userManage","","closeCurrent",""));
		}catch(Exception e) {
			logger.equals(e);
			e.printStackTrace();
			renderJSON(feilure(getText("action.editFeilure")));
		}
		return null;
	}

	public String prepareUpdate() {
		this.prpDuser = this.userService.getUser(this.userCode);
		roleList = (List<PrpRole>) getCache().get("roleList");
		if(roleList == null) {
			roleList = prpRoleService.findRoles();
			getCache().put("roleList", roleList);
		}
		areaInfoList = (List<PrpAreaInfo>) getCache().get("areaInfoList");
		if(areaInfoList == null) {
			areaInfoList = prpAreaInfoService.findAreaInfos();
			getCache().put("areaInfoList", areaInfoList);
		}
		return "success";
	}

	public String add() {
		try {
			prpDuser.setInsertTimeForHis(new Date());
			prpDuser.setOperateTimeForHis(new Date());
			this.userService.save(this.prpDuser);
			renderJSON(success(getText("action.addSuccess"),"userManage","","closeCurrent",""));
		}catch(Exception e) {
			logger.equals(e);
			e.printStackTrace();
			renderJSON(feilure(getText("action.addFeilure")));
			
		}
		return null;
	}

	public String prepareAdd() {
		roleList = (List<PrpRole>) getCache().get("roleList");
		if(roleList == null) {
			roleList = prpRoleService.findRoles();
			getCache().put("roleList", roleList);
		}
		areaInfoList = (List<PrpAreaInfo>) getCache().get("areaInfoList");
		if(areaInfoList == null) {
			areaInfoList = prpAreaInfoService.findAreaInfos();
			getCache().put("areaInfoList", areaInfoList);
		}
		return "success";
	}

	public String delete() {
		try {
		String usercode = getRequest().getParameter("userCode");
		this.userService.delete(usercode);
		renderJSON(success(getText("action.deleteSuccess"),"userManage","","",""));
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			renderJSON(success(getText("action.deleteFeilure"),"userManage"));
		}
		return null;
	}

	public String prepareQuery() {
		return "success";
	}

	public String prepareChangePassword() {
		String userCode = (String) getSession().getAttribute("UserCode");

		this.prpDuser = this.userService.getUser(userCode);

		return "success";
	}

	public String changePassword() {
		try {
			PrpDuser user = (PrpDuser) userService.findUserByUserName(userCode);
			String pd = Sha512DigestUtils.shaHex(password);
			if(pd != null && pd.equals(user.getPassword())) {
				user.setPassword(newPassword);
				userService.update(user);
				renderJSON(success(getText("action.editSuccess"),"","","closeCurrent",""));
			}else {
				renderJSON(feilure(getText("action.passwordError")));
			}
		}catch(Exception e) {
			logger.equals(e);
			e.printStackTrace();
			renderJSON(feilure(getText("action.editFeilure")));
		}
		return null;
	}

	public String view() {
		this.prpDuser = this.userService.getUser(this.userCode);

		return "success";
	}

	public String logout() {
		getSession().invalidate();
		return "success";
	}


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}