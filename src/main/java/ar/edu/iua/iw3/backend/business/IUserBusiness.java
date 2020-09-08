package ar.edu.iua.iw3.backend.business;

import java.util.List;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.User;

public interface IUserBusiness {

	public User load(Integer id) throws NotFoundException, BusinessException;

	public List<User> list() throws BusinessException;
	
	public User add(User user) throws BusinessException;
	
	public User update(User user) throws NotFoundException, BusinessException;

	public User load(String nameOrEmail) throws NotFoundException, BusinessException;

}
