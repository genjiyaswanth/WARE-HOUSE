package com.genji.yaswanth.service;

import java.util.List;

import com.genji.yaswanth.model.WhUserType;

public interface IWhUserTypeService {

	public Integer save(WhUserType wh);

	public List<WhUserType> getAllWhUsers();

	public WhUserType getOneWhuser(Integer id);

	public void deleteWhUser(Integer id);

	public void updateWhUser(WhUserType wh);

}
