package com.splitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

}

/* implements CommandLineRunner {
	private InitService initService;
	private CommandRegistry commandRegistry;

	@Autowired
	public SplitWiseApplication(CommandRegistry commandRegistry, InitService initService){
		this.commandRegistry = commandRegistry;
		this.initService = initService;
	} */

/* // Pre-Initialization
	@Override
	public void run(String... args) throws Exception {
        initService.initialise();
        System.out.println("PRINTING from COMMAND LINE RUNNER");

        // Input for the Application
        // SettleGroup using groupId
        // RegisterUser using name, phoneNumber, password
        // GetUser using userId
        // All the inputs are coming through this run method
        Scanner sc = new Scanner(System.in);
        while (true) {
			String input = sc.nextLine();
			commandRegistry.process(input);
		}
    } */