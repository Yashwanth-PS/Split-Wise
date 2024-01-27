package com.splitwise.service;

import com.splitwise.model.Expense;
import com.splitwise.model.Group;
import com.splitwise.model.User;
import com.splitwise.model.UserExpense;
import com.splitwise.model.constant.Currency;
import com.splitwise.model.constant.UserExpenseType;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.GroupRepository;
import com.splitwise.repository.UserExpenseRepository;
import com.splitwise.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InitServiceImpl implements InitService {
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final UserExpenseRepository userExpenseRepository;

    @Autowired
    public InitServiceImpl(UserRepository userRepository, ExpenseRepository expenseRepository,
                           GroupRepository groupRepository, UserExpenseRepository userExpenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    @Transactional
    public void initialise() {
        // Create 10 Users
        List<User> users = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> User.builder()
                        .email("user" + i + "@gmail.com")
                        .phoneNumber("98765432" + i)
                        .name("User" + i)
                        .password("password" + i)
                        .build())
                .collect(Collectors.toList());

        // Save Users
        userRepository.saveAll(users);

        // Create 2 Groups
        List<Group> groups = IntStream.rangeClosed(1, 2)
                .mapToObj(i -> {
                    Group group = new Group();
                    group.setName("Group" + i);
                    group.setDescription("Description for Group" + i);

                    // Save the group before associating it with users
                    groupRepository.save(group);
                    List<User> groupUsers = users.subList((i - 1) * 5, i * 5);
                    group.setUsers(new ArrayList<>(groupUsers));

                    // Manually set the bidirectional relationship in User entities
                    for (User user : groupUsers) {
                        List<Group> userGroups = user.getGroups();
                        if (userGroups == null) {
                            userGroups = new ArrayList<>();
                            user.setGroups(userGroups);
                        }
                        userGroups.add(group);

                        // Save user to update the relationship
                        userRepository.save(user);
                    }
                    return group;
                })
                .collect(Collectors.toList());

        // Save Groups (optional if you've already saved them inside the loop)
        groupRepository.saveAll(groups);

        // Create 4 Expenses
        List<Expense> expenses = IntStream.rangeClosed(1, 4)
                .mapToObj(i -> {
                    Expense expense = new Expense();
                    expense.setDescription("Expense" + i);
                    expense.setAmount(100 * i); // Expense amount is 100 times the iteration index
                    expense.setCurrency(Currency.INR);

                    // Assign each expense to a group
                    Group group = groups.get((i - 1) % 2);
                    expense.setGroup(group);

                    // Calculate equal share for each user
                    double equalShare = expense.getAmount() / group.getUsers().size();

                    // Create UserExpense for the expense
                    List<UserExpense> userExpenses = group.getUsers().stream()
                            .map(user -> {
                                UserExpense userExpense = new UserExpense();
                                userExpense.setUser(user);

                                if (user.getId() == group.getUsers().get(0).getId()) {
                                    // One user pays the full amount
                                    userExpense.setUserExpenseType(UserExpenseType.PAID);
                                    userExpense.setAmount(expense.getAmount());
                                } else {
                                    // Others share the remaining amount equally
                                    userExpense.setUserExpenseType(UserExpenseType.HAS_TO_PAY);
                                    userExpense.setAmount(equalShare);
                                }

                                userExpense.setExpense(expense);
                                return userExpense;
                            })
                            .collect(Collectors.toList());

                    // Save UserExpenses
                    userExpenseRepository.saveAll(userExpenses);

                    // Update the total amount spent for the group
                    group.setTotalAmountSpend(group.getTotalAmountSpend() + expense.getAmount());

                    return expense;
                })
                .collect(Collectors.toList());

        // Save Expenses
        expenseRepository.saveAll(expenses);

        // Save updated groups (optional if you want to save the groups at this point)
        groupRepository.saveAll(groups);
    }
}