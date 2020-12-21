package com.users.users.service;

import com.users.users.entity.Department;
import com.users.users.entity.User;
import com.users.users.entity.Vo;
import com.users.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;


    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public User saveUser(User user, Long idDepartment){
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + idDepartment, Department.class);
        //check if come with a null pointer exception
        user.setDepartmentId(department.getId());
        return userRepository.save(user);
    }


    public Vo findUserById(Long id) {
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + id, Department.class);
        User user = userRepository.findById(id).orElse(new User(0L, "NOT FOUND", "NOT FOUND", 0L));
        Vo vo = new Vo();
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
