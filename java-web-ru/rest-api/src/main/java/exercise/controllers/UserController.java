package exercise.controllers;

import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();
        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {
        long numericID = Long.parseLong(id);
        // BEGIN
        User user = new QUser()
                .id.equalTo(numericID)
                .findOne();
        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(x -> !x.getFirstName().isEmpty(), "Имя должно содержать хотя бы один символ")
                .check(x -> !x.getLastName().isEmpty(), "Фамилия должна содержать хотя бы один символ")
                .check(x -> !x.getEmail().isEmpty(), "Нужно указать адрес электронной почты")
                .check(x -> EmailValidator.getInstance().isValid(x.getEmail()), "Неправильный адрес электронной почты")
                .check(x -> !x.getPassword().isEmpty(), "Нужно указать пароль")
                .check(x -> StringUtils.isNumeric(x.getPassword()), "В пароле допустимы только цифры")
                .check(x -> x.getPassword().length() > 3, "Пароль не может быть короче четырех символов")
                .get();
        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);
        new QUser()
                .id.equalTo(user.getId())
                .asUpdate()
                .set("firstName", user.getFirstName())
                .set("lastName", user.getLastName())
                .set("email", user.getEmail())
                .set("password", user.getPassword())
                .update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        long numericID = Long.parseLong(id);
        new QUser()
                .id.equalTo(numericID)
                .delete();
        // END
    };
}
