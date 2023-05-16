package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("t_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private Long id;

	/**
	 * 用户名
	 */
//	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String userName;

	/**
	 * 密码
	 */
//	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
//	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
//	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

	/**
	 * 创建者ID
	 */
	private Long createUserId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 项目ID列表
	 */
	@TableField(exist=false)
	private List<Integer> projectIdList;

	@ApiModelProperty("下发角色列表")
	@TableField(exist=false)
	private List<Integer> projectRoleIdList;

	@ApiModelProperty("0:正匠开通账户，1：开通子账户")
	private int type;

	@ApiModelProperty("项目名称")
	@TableField(exist = false)
	private String projectName;

	private String realName;

	@TableField(exist = false)
	private String realNameInfo;

//	@TableField(exist = false)
//	private List<WorkerInfoVo> realNameList;


	@TableField(exist = false)
	@ApiModelProperty("菜单id集")
	private String menuIds;

	@TableField(exist = false)
	@ApiModelProperty("菜单id字符集")
	private String menuArr;

}
