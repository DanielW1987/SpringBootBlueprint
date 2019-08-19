package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.response.PaginationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeesJSController {

  private final EmployeeService employeeService;
  private static final int ITEMS_PER_PAGE = 2;

  @Autowired
  public EmployeesJSController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(Endpoints.EMPLOYEES_JS_LIST)
  public ModelAndView showEmployeesList(@RequestParam(name = "page", defaultValue = "1") int page) {
    List<EmployeeDto> employeeDtos = employeeService.findAll(page, ITEMS_PER_PAGE);

    Map<String, Object> viewModel = new HashMap<>();
    viewModel.put("employees", employeeDtos);
    viewModel.put("createUrl", Endpoints.EMPLOYEES_CREATE);
    viewModel.put("pagination", createPaginationResponse(page));

    return new ModelAndView(ViewNames.EMPLOYEES_JS_LIST, viewModel);
  }

  private PaginationResponseDto createPaginationResponse(int currentPage) {
    long employeeCount = employeeService.getCount();
    int numberOfPages  = (int) Math.ceil((double) employeeCount / (double) ITEMS_PER_PAGE);

    return PaginationResponseDto.builder()
            .numberOfPages(numberOfPages)
            .currentPage(currentPage)
            .itemsPerPage(ITEMS_PER_PAGE)
            .build();
  }

}
