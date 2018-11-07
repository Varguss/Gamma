package ru.gamma_station.domain.website;

/**
 * RULES_MANAGEMENT - доступ на странице с правилами.
 * CREW_MANAGEMENT - доступ на странице состава.
 * BAN_MANAGEMENT - доступ к выдаче банов.
 * POSTS_MANAGEMENT - доступ к главной странице.
 * STAFF_MANAGEMENT - доступ к созданию новых админов и редактированию прав.
 */
public enum Authority {
    RULES_MANAGEMENT, CREW_MANAGEMENT, BAN_MANAGEMENT, POSTS_MANAGEMENT, STAFF_MANAGEMENT
}
