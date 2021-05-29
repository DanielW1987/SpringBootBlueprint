package com.wagner.blueprint.service.impl;

import com.wagner.blueprint.model.entity.Employee;
import com.wagner.blueprint.model.repository.EmployeeRepository;
import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.service.EmployeeMapper;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
  }

  @NotNull
  @Override
  public List<EmployeeDto> findAll() {
    return employeeRepository.findAll()
            .stream()
            .map(employeeMapper::map)
            .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public List<EmployeeDto> findAll(int currentPage, int itemsPerPage) {
    // PageRequest is index based, web frontend is not
    Pageable pageRequest = PageRequest.of(currentPage - 1, itemsPerPage);
    return employeeRepository.findAll(pageRequest)
            .stream()
            .map(employeeMapper::map)
            .collect(Collectors.toList());
  }

  @Override
  public List<EmployeeDto> findByTeamName(String teamName) {
    return employeeRepository.findByTeamName(teamName)
            .stream()
            .map(employeeMapper::map)
            .collect(Collectors.toList());
  }

  @Override
  public long getCount() {
    return employeeRepository.count();
  }

  @NotNull
  @Override
  public Optional<EmployeeDto> find(long id) {
    Optional<Employee> employee = employeeRepository.findById(id);

    return employee.map(employeeMapper::map);
  }

  @NotNull
  @Override
  public EmployeeDto create(@NotNull EmployeeRequestDto request) {
    Employee employee = employeeMapper.map(request);
    employeeRepository.save(employee);

    return employeeMapper.map(employee);
  }

  @NotNull
  @Override
  public List<EmployeeDto> createAll(@NotNull Iterable<EmployeeRequestDto> request) {
    List<Employee> employees = new ArrayList<>();
    request.forEach(employeeRequestDto -> employees.add(employeeMapper.map(employeeRequestDto)));

    employeeRepository.saveAll(employees);

    return employees.stream()
            .map(employeeMapper::map)
            .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public Optional<EmployeeDto> update(long id, @NotNull EmployeeRequestDto request) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    EmployeeDto employeeResponse        = null;

    if (employeeOptional.isPresent()) {
      Employee employee = employeeMapper.map(request);
      employeeRepository.save(employee);
      employeeResponse = employeeMapper.map(employee);
    }

    return Optional.ofNullable(employeeResponse);
  }

  @Override
  public boolean delete(long id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
      return true;
    }

    // entity doesn't exist
    return false;
  }
}
