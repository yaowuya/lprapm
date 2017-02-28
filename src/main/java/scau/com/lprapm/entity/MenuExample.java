package scau.com.lprapm.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuExample() {
        oredCriteria = new ArrayList<Criteria>();
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
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Integer value) {
            addCriterion("menu_id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Integer value) {
            addCriterion("menu_id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Integer value) {
            addCriterion("menu_id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Integer value) {
            addCriterion("menu_id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("menu_id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Integer> values) {
            addCriterion("menu_id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Integer> values) {
            addCriterion("menu_id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("menu_id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_id not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("menu_name is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("menu_name is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("menu_name =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("menu_name <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("menu_name >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("menu_name >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("menu_name <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("menu_name <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("menu_name like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("menu_name not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("menu_name in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("menu_name not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("menu_name between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("menu_name not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuIClassIsNull() {
            addCriterion("menu_i_class is null");
            return (Criteria) this;
        }

        public Criteria andMenuIClassIsNotNull() {
            addCriterion("menu_i_class is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIClassEqualTo(String value) {
            addCriterion("menu_i_class =", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassNotEqualTo(String value) {
            addCriterion("menu_i_class <>", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassGreaterThan(String value) {
            addCriterion("menu_i_class >", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassGreaterThanOrEqualTo(String value) {
            addCriterion("menu_i_class >=", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassLessThan(String value) {
            addCriterion("menu_i_class <", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassLessThanOrEqualTo(String value) {
            addCriterion("menu_i_class <=", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassLike(String value) {
            addCriterion("menu_i_class like", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassNotLike(String value) {
            addCriterion("menu_i_class not like", value, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassIn(List<String> values) {
            addCriterion("menu_i_class in", values, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassNotIn(List<String> values) {
            addCriterion("menu_i_class not in", values, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassBetween(String value1, String value2) {
            addCriterion("menu_i_class between", value1, value2, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuIClassNotBetween(String value1, String value2) {
            addCriterion("menu_i_class not between", value1, value2, "menuIClass");
            return (Criteria) this;
        }

        public Criteria andMenuHrefIsNull() {
            addCriterion("menu_href is null");
            return (Criteria) this;
        }

        public Criteria andMenuHrefIsNotNull() {
            addCriterion("menu_href is not null");
            return (Criteria) this;
        }

        public Criteria andMenuHrefEqualTo(String value) {
            addCriterion("menu_href =", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefNotEqualTo(String value) {
            addCriterion("menu_href <>", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefGreaterThan(String value) {
            addCriterion("menu_href >", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefGreaterThanOrEqualTo(String value) {
            addCriterion("menu_href >=", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefLessThan(String value) {
            addCriterion("menu_href <", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefLessThanOrEqualTo(String value) {
            addCriterion("menu_href <=", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefLike(String value) {
            addCriterion("menu_href like", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefNotLike(String value) {
            addCriterion("menu_href not like", value, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefIn(List<String> values) {
            addCriterion("menu_href in", values, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefNotIn(List<String> values) {
            addCriterion("menu_href not in", values, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefBetween(String value1, String value2) {
            addCriterion("menu_href between", value1, value2, "menuHref");
            return (Criteria) this;
        }

        public Criteria andMenuHrefNotBetween(String value1, String value2) {
            addCriterion("menu_href not between", value1, value2, "menuHref");
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