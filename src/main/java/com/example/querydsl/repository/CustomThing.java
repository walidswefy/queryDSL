package com.example.querydsl.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author walid.sewaify
 * @since 05-Feb-21
 */
public interface CustomThing<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {


    static BooleanExpression applyStringComparison(Path<String> path, Collection<? extends String> strings) {
        BooleanExpression result = null;

        for (String s : strings) {
            try {
                final String[] parts = s.split(",");
                final String operator = parts[0];
                final String value = parts.length > 1 ? parts[1] : null;

                final Method method = Arrays.stream(path.getClass().getMethods())
                    .filter(m -> operator.equals(m.getName()))
                    .filter(m -> BooleanExpression.class.equals(m.getReturnType()))
                    .filter(m -> m.getParameterTypes().length == (value == null ? 0 : 1))
                    .filter(m -> value == null || m.getParameterTypes()[0].equals(String.class) || m.getParameterTypes()[0].equals(Object.class))
                    .findFirst().get();

                final BooleanExpression be;
                if (value == null) {
                    be = (BooleanExpression) method.invoke(path);
                } else {
                    be = (BooleanExpression) method.invoke(path, value);
                }

                result = result == null ? be : result.and(be);

            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }

        return result;

    }

    @Override
    public default void customize(QuerydslBindings bindings, T root) {
        bindings.bind(String.class).all((stringPath, collection) -> java.util.Optional.ofNullable(applyStringComparison(stringPath, collection)));
    }
}
