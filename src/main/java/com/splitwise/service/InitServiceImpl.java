package com.splitwise.service;

import com.splitwise.model.constant.Currency;
import com.splitwise.model.constant.UserExpenseType;
import com.splitwise.model.Expense;
import com.splitwise.model.Group;
import com.splitwise.model.User;
import com.splitwise.model.UserExpense;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.GroupRepository;
import com.splitwise.repository.UserExpenseRepository;
import com.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InitServiceImpl implements InitService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Override
    public void initialise() {
        Optional<User> yash = Optional.ofNullable(User.builder()
                .email("yash@gmail.com")
                .phoneNumber("9876543210")
                .name("Yash")
                .build());
        Optional<User> nikki = Optional.ofNullable(User.builder()
                .email("nikki@gmail.com")
                .phoneNumber("9876543211")
                .name("Nikki")
                .build());
        Optional<User> yashwa = Optional.ofNullable(User.builder()
                .email("yashwa@gmail.com")
                .phoneNumber("9876543212")
                .name("Yashwa")
                .build());
        Optional<User> niks = Optional.ofNullable(User.builder()
                .email("niks@gmail.com")
                .phoneNumber("9876543213")
                .name("Niks")
                .build());
        yash = userRepository.save(yash.get());
        nikki = userRepository.save(nikki.get());
        yashwa = userRepository.save(yashwa.get());
        niks = userRepository.save(niks.get());

        Group group = new Group();
        group.setDescription("Friends who never pay back on time");
        group.setName("Friends");
        group.setUsers(List.of(yash.get(), nikki.get(), yashwa.get(), niks.get()));

        group = groupRepository.save(group);

        // 4 Expenses
        // Expense 1 -> Amount - 1000, Paid By - Nikki, Has To pay - Everyone Equal
        UserExpense userExpense = new UserExpense();
        userExpense.setUser(nikki.get());
        userExpense.setAmount(1000);
        userExpense.setUserExpenseType(UserExpenseType.PAID);
        userExpense = userExpenseRepository.save(userExpense);

        UserExpense userExpense1 = new UserExpense();
        userExpense1.setUser(nikki.get());
        userExpense1.setAmount(250);
        userExpense1.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense1 = userExpenseRepository.save(userExpense1);

        UserExpense userExpense2 = new UserExpense();
        userExpense2.setUser(yash.get());
        userExpense2.setAmount(250);
        userExpense2.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense2 = userExpenseRepository.save(userExpense2);

        UserExpense userExpense3 = new UserExpense();
        userExpense3.setUser(niks.get());
        userExpense3.setAmount(250);
        userExpense3.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense3 = userExpenseRepository.save(userExpense3);

        UserExpense userExpense4 = new UserExpense();
        userExpense4.setUser(yashwa.get());
        userExpense4.setAmount(250);
        userExpense4.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
        userExpense4 = userExpenseRepository.save(userExpense4);

        Expense expense = new Expense();
        expense.setAmount(1000);
        expense.setDescription("Dinner");
        expense.setCurrency(Currency.INR);
        expense.setUserExpenses(List.of(userExpense, userExpense1, userExpense2, userExpense3, userExpense4));

        expenseRepository.save(expense);
    }
}