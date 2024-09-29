package com.fdifrison.catan.core.repository.specification;

import com.fdifrison.catan.core.entity.Player;
import com.fdifrison.catan.core.entity.Player_;
import com.fdifrison.catan.core.filter.PlayerFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecification implements Specification<Player> {
    private static final String LIKE_TEMPLATE = "%%%s%%";

    private final PlayerFilter filter;

    public PlayerSpecification(PlayerFilter filter) {
        this.filter = filter;
    }

    private static Specification<Player> usernameLike(String username) {
        return (root, query, criteriaBuilder) -> username != null
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(Player_.username)),
                        LIKE_TEMPLATE.formatted(username.toLowerCase()))
                : null;
    }

    private static Specification<Player> emailLike(String email) {
        return (root, query, criteriaBuilder) -> email != null
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(Player_.email)), LIKE_TEMPLATE.formatted(email.toLowerCase()))
                : null;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(usernameLike(filter.username()), emailLike(filter.email()))
                .toPredicate(root, query, criteriaBuilder);
    }
}
