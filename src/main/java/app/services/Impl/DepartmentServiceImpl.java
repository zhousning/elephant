package app.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.DepartmentDao;
import app.models.Department;
import app.services.DepartmentService;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

}