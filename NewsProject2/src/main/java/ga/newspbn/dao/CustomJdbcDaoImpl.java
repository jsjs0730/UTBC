/**
 * 
 */
package ga.newspbn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import ga.newspbn.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class CustomJdbcDaoImpl extends JdbcDaoImpl {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<UserDetails> users = loadUsersByUsername(username);
		if(users.size() == 0) {
			logger.debug("Query returned no result for user '" + username + "'");
			UsernameNotFoundException ue = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[] {username}, "Username{0} not found"));
			throw ue;
		}
		
		MemberVO user = (MemberVO)users.get(0); //contains no GrantedAuthority[]
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		
		if(getEnableAuthorities()) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}
		
		if(getEnableGroups()) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}
		
		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
		user.setAuthorities(dbAuths);
		
		if(dbAuths.size() == 0) {
			logger.debug("User '" + username + "'has no authorities and will be treated as 'not found'");
			UsernameNotFoundException ue = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] {username}, "User{0} has no GrantedAuthority"));
			throw ue;
		}
		return user;
	}	
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] {username}, new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException{
				String uid = rs.getString(1);
				String upw = rs.getString(2);
				String uname = rs.getString(3);
				String email = rs.getString(4);
				String birthdate = rs.getString(5);
				Date joindate = rs.getDate(6);
				String gender = rs.getString(7);
				String profile_picture = rs.getString(8);
				String filesrc = rs.getString(9);
				String profile_content = rs.getString(10);
				return new MemberVO(uid, upw, uname, email, birthdate, joindate, gender, profile_picture, filesrc, profile_content,  AuthorityUtils.NO_AUTHORITIES);
			}
		});
	}
	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {username}, new RowMapper<GrantedAuthority>() {
            public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = getRolePrefix() + rs.getString(1);
                return new SimpleGrantedAuthority(roleName);
            }
        });
	}
	@Override
	protected List<GrantedAuthority> loadGroupAuthorities(String username) {
		return super.loadGroupAuthorities(username);
	}

	
	
}
