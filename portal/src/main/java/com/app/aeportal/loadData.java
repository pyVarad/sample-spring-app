package com.app.aeportal;

import com.app.aeportal.services.UserService;
import com.app.aeportal.constant.DomainConstant;
import com.app.aeportal.domain.*;
import com.app.aeportal.dto.request.UserRequestDto;
import com.app.aeportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;

@Component
public class loadData implements CommandLineRunner {


    private final EmployeeRepository employeeRepository;
    private final DesignationRepository designationRepository;
    private final SkillsRepository skillsRepository;
    private final LocationRepository locationRepository;
    private final ProjectsRepository projectsRepository;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final UserService userService;

    @Autowired
    public loadData(
            EmployeeRepository employeeRepository,
            DesignationRepository designationRepository,
            SkillsRepository skillsRepository,
            LocationRepository locationRepository,
            ProjectsRepository projectsRepository,
            UserRepository userRepository,
            RolesRepository rolesRepository,
            UserService userService
    ) {
        this.employeeRepository = employeeRepository;
        this.designationRepository = designationRepository;
        this.skillsRepository = skillsRepository;
        this.locationRepository = locationRepository;
        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        /* Designation */

        Designation seniorManager = new Designation("Senior Manager", 22, 1);
        this.designationRepository.save(seniorManager);

        Designation manager = new Designation("Manager", 32, 1);
        this.designationRepository.save(manager);

        Designation senior = new Designation("Senior Developer", 42, 1);
        this.designationRepository.save(senior);

        Designation staff = new Designation("Junior Developer", 52, 1);
        this.designationRepository.save(staff);

        /* Skills */

        Skills backendJava = new Skills("Microservices", "Springboot");
        this.skillsRepository.save(backendJava);

        Skills backendPython = new Skills("Microservices", "Django");
        this.skillsRepository.save(backendPython);

        Skills frontendAngular = new Skills("UI", "Angular");
        this.skillsRepository.save(frontendAngular);

        Skills frontendReact = new Skills("UI", "React");
        this.skillsRepository.save(frontendReact);

        Skills frontendVue = new Skills("UI", "Vue");
        this.skillsRepository.save(frontendVue);

        /* Location */

        Location trivandrum = new Location("Trivandrum", "India");
        this.locationRepository.save(trivandrum);

        Location chennai = new Location("Chennai", "India");
        this.locationRepository.save(chennai);

        Location gurgaon = new Location("Gurgaon", "India");
        this.locationRepository.save(gurgaon);

        Location bangalore = new Location("Bangalore", "India");
        this.locationRepository.save(bangalore);

        Location kolkata = new Location("Kolkata", "India");
        this.locationRepository.save(kolkata);

        /* Projects */
        String endDate = "2025-04-08 12:30";
        String startDate = "2021-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        LocalDateTime startDateTime = LocalDateTime.parse(endDate, formatter);

        Projects nexusBanking = new Projects("Nexus for Banking", startDateTime, endDateTime, "1001");
        this.projectsRepository.save(nexusBanking);

        Projects nexusInsurance = new Projects("Nexus for Insurance", startDateTime, endDateTime, "1002");
        this.projectsRepository.save(nexusInsurance);

        Projects nexusMortgages = new Projects("Nexus for Mortgages", startDateTime, endDateTime, "1003");
        this.projectsRepository.save(nexusMortgages);


        /* Employees */
        Employee varad = new Employee(
                "Varadarajan",
                "Govindamohan",
                "IN010M63916",
                "varad.g@test.com",
                true,
                DomainConstant.Gender.Male,
                DomainConstant.Segment.FS
        );
        varad.setProjects(Set.of(nexusBanking, nexusInsurance, nexusMortgages));
        varad.setDesignation(manager);
        varad.setLocation(bangalore);
        varad.setSkills(Set.of(backendJava, backendPython, frontendAngular, frontendReact));
        this.employeeRepository.save(varad);

        /* Roles */
        Roles admin = new Roles("admin");
        Roles superAdmin = new Roles("superAdmin");
        Roles user = new Roles("user");

        admin = this.rolesRepository.save(admin);
        superAdmin = this.rolesRepository.save(superAdmin);
        user = this.rolesRepository.save(user);

        /* Users */
        UserRequestDto john = new UserRequestDto();
        john.setFirstName("John");
        john.setLastName("Strauss");
        john.setUsername("jstrauss");
        john.setPassword("password123");
        john.setRoles(Arrays.asList(admin.getId(), user.getId()));
        this.userService.addNewUser(john);

        UserRequestDto mark = new UserRequestDto();
        mark.setFirstName("Mark");
        mark.setLastName("Streatfield");
        mark.setUsername("mstreatfield");
        mark.setPassword("password123");
        mark.setRoles(Arrays.asList(admin.getId(), user.getId(), superAdmin.getId()));
        this.userService.addNewUser(mark);

        UserRequestDto varadag = new UserRequestDto();
        varadag.setFirstName("Varad");
        varadag.setLastName("AG");
        varadag.setUsername("agvarad");
        varadag.setPassword("password123");
        varadag.setRoles(Arrays.asList(user.getId(), superAdmin.getId()));
        this.userService.addNewUser(varadag);
    }
}
