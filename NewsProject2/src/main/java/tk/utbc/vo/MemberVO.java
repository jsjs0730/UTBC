/**
 * 
 */
package tk.utbc.vo;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class MemberVO implements UserDetails {

	private static final long serialVersionUID = -5113971372521237576L;

	private String uid;
	private String upw;
	private String uname;
	private String email;
	private String birthdate;
	private Date joindate;
	private String gender;
	private String profile_picture;
	private String filesrc;
	private String profile_content;
	private String auth;
	
	private Set<GrantedAuthority> authorities;//계정이 가지고 있는 권한 목록

	public MemberVO() {}


	public MemberVO(String uid, String upw, String uname, String email, String birthdate, Date joindate, String gender,
			String profile_picture, String filesrc, String profile_content, Collection<? extends GrantedAuthority> authorities) {
		this.uid = uid;
		this.upw = upw;
		this.uname = uname;
		this.email = email;
		this.birthdate = birthdate;
		this.joindate = joindate;
		this.gender = gender;
		this.profile_picture = profile_picture;
		this.filesrc = filesrc;
		this.profile_content = profile_content;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}
	


	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthdate;
	}
	public void setBirthday(String birthdate) {
		this.birthdate = birthdate;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	public String getProfile_content() {
		return profile_content;
	}
	public void setProfile_content(String profile_content) {
		this.profile_content = profile_content;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}
	public String getFilesrc() {
		return filesrc;
	}
	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
	}

	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}




	@Override
	public String toString() {
		return "MemberVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", email=" + email + ", birthdate="
				+ birthdate + ", joindate=" + joindate + ", gender=" + gender + ", profile_picture=" + profile_picture
				+ ", filesrc=" + filesrc + ", profile_content=" + profile_content + ", auth=" + auth + ", authorities="
				+ authorities + "]";
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return upw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUid();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities){
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
		
		for(GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}
		return sortedAuthorities;
	}
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority>{
		
		private static final long SerialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
				
		@Override
		public int compare(GrantedAuthority o1, GrantedAuthority o2) {
			if(o2.getAuthority() == null) {
				return -1;			
			}
			if(o1.getAuthority() == null) {
				return -1;
			}
			
			return o1.getAuthority().compareTo(o2.getAuthority());
		}
		
	}

	
}
