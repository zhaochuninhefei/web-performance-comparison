package com.zhaochuninhefei.webpmsbtvt.db.po;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountsExample {
    /**
     * 排序.
     */
    protected String orderByClause;

    /**
     * 是否去重.
     */
    protected boolean distinct;

    /**
     * 条件列表.
     */
    protected List<Criteria> oredCriteria;

    public AccountsExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> createdAtCriteria;

        protected List<Criterion> updatedAtCriteria;

        protected List<Criterion> deletedAtCriteria;

        protected List<Criterion> actRegisterDateCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
            createdAtCriteria = new ArrayList<>();
            updatedAtCriteria = new ArrayList<>();
            deletedAtCriteria = new ArrayList<>();
            actRegisterDateCriteria = new ArrayList<>();
        }

        public List<Criterion> getCreatedAtCriteria() {
            return createdAtCriteria;
        }

        protected void addCreatedAtCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            createdAtCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addCreatedAtCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            createdAtCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getUpdatedAtCriteria() {
            return updatedAtCriteria;
        }

        protected void addUpdatedAtCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            updatedAtCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addUpdatedAtCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            updatedAtCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getDeletedAtCriteria() {
            return deletedAtCriteria;
        }

        protected void addDeletedAtCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            deletedAtCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addDeletedAtCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            deletedAtCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getActRegisterDateCriteria() {
            return actRegisterDateCriteria;
        }

        protected void addActRegisterDateCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            actRegisterDateCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addActRegisterDateCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            actRegisterDateCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || createdAtCriteria.size() > 0
                || updatedAtCriteria.size() > 0
                || deletedAtCriteria.size() > 0
                || actRegisterDateCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(createdAtCriteria);
                allCriteria.addAll(updatedAtCriteria);
                allCriteria.addAll(deletedAtCriteria);
                allCriteria.addAll(actRegisterDateCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(LocalDateTime value) {
            addCreatedAtCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(LocalDateTime value) {
            addCreatedAtCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(LocalDateTime value) {
            addCreatedAtCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCreatedAtCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(LocalDateTime value) {
            addCreatedAtCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCreatedAtCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<LocalDateTime> values) {
            addCreatedAtCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<LocalDateTime> values) {
            addCreatedAtCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCreatedAtCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCreatedAtCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(LocalDateTime value) {
            addUpdatedAtCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(LocalDateTime value) {
            addUpdatedAtCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(LocalDateTime value) {
            addUpdatedAtCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addUpdatedAtCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(LocalDateTime value) {
            addUpdatedAtCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(LocalDateTime value) {
            addUpdatedAtCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<LocalDateTime> values) {
            addUpdatedAtCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<LocalDateTime> values) {
            addUpdatedAtCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addUpdatedAtCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addUpdatedAtCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIsNull() {
            addCriterion("deleted_at is null");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIsNotNull() {
            addCriterion("deleted_at is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedAtEqualTo(LocalDateTime value) {
            addDeletedAtCriterion("deleted_at =", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotEqualTo(LocalDateTime value) {
            addDeletedAtCriterion("deleted_at <>", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtGreaterThan(LocalDateTime value) {
            addDeletedAtCriterion("deleted_at >", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addDeletedAtCriterion("deleted_at >=", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtLessThan(LocalDateTime value) {
            addDeletedAtCriterion("deleted_at <", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtLessThanOrEqualTo(LocalDateTime value) {
            addDeletedAtCriterion("deleted_at <=", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIn(List<LocalDateTime> values) {
            addDeletedAtCriterion("deleted_at in", values, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotIn(List<LocalDateTime> values) {
            addDeletedAtCriterion("deleted_at not in", values, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addDeletedAtCriterion("deleted_at between", value1, value2, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addDeletedAtCriterion("deleted_at not between", value1, value2, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andActNameIsNull() {
            addCriterion("act_name is null");
            return (Criteria) this;
        }

        public Criteria andActNameIsNotNull() {
            addCriterion("act_name is not null");
            return (Criteria) this;
        }

        public Criteria andActNameEqualTo(String value) {
            addCriterion("act_name =", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotEqualTo(String value) {
            addCriterion("act_name <>", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameGreaterThan(String value) {
            addCriterion("act_name >", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameGreaterThanOrEqualTo(String value) {
            addCriterion("act_name >=", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameLessThan(String value) {
            addCriterion("act_name <", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameLessThanOrEqualTo(String value) {
            addCriterion("act_name <=", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameLike(String value) {
            addCriterion("act_name like", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotLike(String value) {
            addCriterion("act_name not like", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameIn(List<String> values) {
            addCriterion("act_name in", values, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotIn(List<String> values) {
            addCriterion("act_name not in", values, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameBetween(String value1, String value2) {
            addCriterion("act_name between", value1, value2, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotBetween(String value1, String value2) {
            addCriterion("act_name not between", value1, value2, "actName");
            return (Criteria) this;
        }

        public Criteria andActPwdIsNull() {
            addCriterion("act_pwd is null");
            return (Criteria) this;
        }

        public Criteria andActPwdIsNotNull() {
            addCriterion("act_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andActPwdEqualTo(String value) {
            addCriterion("act_pwd =", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdNotEqualTo(String value) {
            addCriterion("act_pwd <>", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdGreaterThan(String value) {
            addCriterion("act_pwd >", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdGreaterThanOrEqualTo(String value) {
            addCriterion("act_pwd >=", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdLessThan(String value) {
            addCriterion("act_pwd <", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdLessThanOrEqualTo(String value) {
            addCriterion("act_pwd <=", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdLike(String value) {
            addCriterion("act_pwd like", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdNotLike(String value) {
            addCriterion("act_pwd not like", value, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdIn(List<String> values) {
            addCriterion("act_pwd in", values, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdNotIn(List<String> values) {
            addCriterion("act_pwd not in", values, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdBetween(String value1, String value2) {
            addCriterion("act_pwd between", value1, value2, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActPwdNotBetween(String value1, String value2) {
            addCriterion("act_pwd not between", value1, value2, "actPwd");
            return (Criteria) this;
        }

        public Criteria andActNickNameIsNull() {
            addCriterion("act_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andActNickNameIsNotNull() {
            addCriterion("act_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andActNickNameEqualTo(String value) {
            addCriterion("act_nick_name =", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameNotEqualTo(String value) {
            addCriterion("act_nick_name <>", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameGreaterThan(String value) {
            addCriterion("act_nick_name >", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("act_nick_name >=", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameLessThan(String value) {
            addCriterion("act_nick_name <", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameLessThanOrEqualTo(String value) {
            addCriterion("act_nick_name <=", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameLike(String value) {
            addCriterion("act_nick_name like", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameNotLike(String value) {
            addCriterion("act_nick_name not like", value, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameIn(List<String> values) {
            addCriterion("act_nick_name in", values, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameNotIn(List<String> values) {
            addCriterion("act_nick_name not in", values, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameBetween(String value1, String value2) {
            addCriterion("act_nick_name between", value1, value2, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActNickNameNotBetween(String value1, String value2) {
            addCriterion("act_nick_name not between", value1, value2, "actNickName");
            return (Criteria) this;
        }

        public Criteria andActIntroductionIsNull() {
            addCriterion("act_introduction is null");
            return (Criteria) this;
        }

        public Criteria andActIntroductionIsNotNull() {
            addCriterion("act_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andActIntroductionEqualTo(String value) {
            addCriterion("act_introduction =", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionNotEqualTo(String value) {
            addCriterion("act_introduction <>", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionGreaterThan(String value) {
            addCriterion("act_introduction >", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("act_introduction >=", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionLessThan(String value) {
            addCriterion("act_introduction <", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionLessThanOrEqualTo(String value) {
            addCriterion("act_introduction <=", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionLike(String value) {
            addCriterion("act_introduction like", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionNotLike(String value) {
            addCriterion("act_introduction not like", value, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionIn(List<String> values) {
            addCriterion("act_introduction in", values, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionNotIn(List<String> values) {
            addCriterion("act_introduction not in", values, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionBetween(String value1, String value2) {
            addCriterion("act_introduction between", value1, value2, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActIntroductionNotBetween(String value1, String value2) {
            addCriterion("act_introduction not between", value1, value2, "actIntroduction");
            return (Criteria) this;
        }

        public Criteria andActStatusIsNull() {
            addCriterion("act_status is null");
            return (Criteria) this;
        }

        public Criteria andActStatusIsNotNull() {
            addCriterion("act_status is not null");
            return (Criteria) this;
        }

        public Criteria andActStatusEqualTo(Byte value) {
            addCriterion("act_status =", value, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusNotEqualTo(Byte value) {
            addCriterion("act_status <>", value, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusGreaterThan(Byte value) {
            addCriterion("act_status >", value, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("act_status >=", value, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusLessThan(Byte value) {
            addCriterion("act_status <", value, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusLessThanOrEqualTo(Byte value) {
            addCriterion("act_status <=", value, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusIn(List<Byte> values) {
            addCriterion("act_status in", values, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusNotIn(List<Byte> values) {
            addCriterion("act_status not in", values, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusBetween(Byte value1, Byte value2) {
            addCriterion("act_status between", value1, value2, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("act_status not between", value1, value2, "actStatus");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateIsNull() {
            addCriterion("act_register_date is null");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateIsNotNull() {
            addCriterion("act_register_date is not null");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateEqualTo(LocalDateTime value) {
            addActRegisterDateCriterion("act_register_date =", value, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateNotEqualTo(LocalDateTime value) {
            addActRegisterDateCriterion("act_register_date <>", value, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateGreaterThan(LocalDateTime value) {
            addActRegisterDateCriterion("act_register_date >", value, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateGreaterThanOrEqualTo(LocalDateTime value) {
            addActRegisterDateCriterion("act_register_date >=", value, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateLessThan(LocalDateTime value) {
            addActRegisterDateCriterion("act_register_date <", value, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateLessThanOrEqualTo(LocalDateTime value) {
            addActRegisterDateCriterion("act_register_date <=", value, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateIn(List<LocalDateTime> values) {
            addActRegisterDateCriterion("act_register_date in", values, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateNotIn(List<LocalDateTime> values) {
            addActRegisterDateCriterion("act_register_date not in", values, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addActRegisterDateCriterion("act_register_date between", value1, value2, "actRegisterDate");
            return (Criteria) this;
        }

        public Criteria andActRegisterDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addActRegisterDateCriterion("act_register_date not between", value1, value2, "actRegisterDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}