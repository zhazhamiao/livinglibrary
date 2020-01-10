package com.livinglibrary.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationrecordExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public RegistrationrecordExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
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

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andGuestidIsNull() {
			addCriterion("guestId is null");
			return (Criteria) this;
		}

		public Criteria andGuestidIsNotNull() {
			addCriterion("guestId is not null");
			return (Criteria) this;
		}

		public Criteria andGuestidEqualTo(Integer value) {
			addCriterion("guestId =", value, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidNotEqualTo(Integer value) {
			addCriterion("guestId <>", value, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidGreaterThan(Integer value) {
			addCriterion("guestId >", value, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidGreaterThanOrEqualTo(Integer value) {
			addCriterion("guestId >=", value, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidLessThan(Integer value) {
			addCriterion("guestId <", value, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidLessThanOrEqualTo(Integer value) {
			addCriterion("guestId <=", value, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidIn(List<Integer> values) {
			addCriterion("guestId in", values, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidNotIn(List<Integer> values) {
			addCriterion("guestId not in", values, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidBetween(Integer value1, Integer value2) {
			addCriterion("guestId between", value1, value2, "guestid");
			return (Criteria) this;
		}

		public Criteria andGuestidNotBetween(Integer value1, Integer value2) {
			addCriterion("guestId not between", value1, value2, "guestid");
			return (Criteria) this;
		}

		public Criteria andStateIsNull() {
			addCriterion("state is null");
			return (Criteria) this;
		}

		public Criteria andStateIsNotNull() {
			addCriterion("state is not null");
			return (Criteria) this;
		}

		public Criteria andStateEqualTo(Integer value) {
			addCriterion("state =", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotEqualTo(Integer value) {
			addCriterion("state <>", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThan(Integer value) {
			addCriterion("state >", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThanOrEqualTo(Integer value) {
			addCriterion("state >=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThan(Integer value) {
			addCriterion("state <", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThanOrEqualTo(Integer value) {
			addCriterion("state <=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateIn(List<Integer> values) {
			addCriterion("state in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotIn(List<Integer> values) {
			addCriterion("state not in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateBetween(Integer value1, Integer value2) {
			addCriterion("state between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotBetween(Integer value1, Integer value2) {
			addCriterion("state not between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStuidIsNull() {
			addCriterion("stuid is null");
			return (Criteria) this;
		}

		public Criteria andStuidIsNotNull() {
			addCriterion("stuid is not null");
			return (Criteria) this;
		}

		public Criteria andStuidEqualTo(String value) {
			addCriterion("stuid =", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidNotEqualTo(String value) {
			addCriterion("stuid <>", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidGreaterThan(String value) {
			addCriterion("stuid >", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidGreaterThanOrEqualTo(String value) {
			addCriterion("stuid >=", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidLessThan(String value) {
			addCriterion("stuid <", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidLessThanOrEqualTo(String value) {
			addCriterion("stuid <=", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidLike(String value) {
			addCriterion("stuid like", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidNotLike(String value) {
			addCriterion("stuid not like", value, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidIn(List<String> values) {
			addCriterion("stuid in", values, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidNotIn(List<String> values) {
			addCriterion("stuid not in", values, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidBetween(String value1, String value2) {
			addCriterion("stuid between", value1, value2, "stuid");
			return (Criteria) this;
		}

		public Criteria andStuidNotBetween(String value1, String value2) {
			addCriterion("stuid not between", value1, value2, "stuid");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeIsNull() {
			addCriterion("ArrivalTime is null");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeIsNotNull() {
			addCriterion("ArrivalTime is not null");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeEqualTo(Date value) {
			addCriterion("ArrivalTime =", value, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeNotEqualTo(Date value) {
			addCriterion("ArrivalTime <>", value, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeGreaterThan(Date value) {
			addCriterion("ArrivalTime >", value, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeGreaterThanOrEqualTo(Date value) {
			addCriterion("ArrivalTime >=", value, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeLessThan(Date value) {
			addCriterion("ArrivalTime <", value, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeLessThanOrEqualTo(Date value) {
			addCriterion("ArrivalTime <=", value, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeIn(List<Date> values) {
			addCriterion("ArrivalTime in", values, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeNotIn(List<Date> values) {
			addCriterion("ArrivalTime not in", values, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeBetween(Date value1, Date value2) {
			addCriterion("ArrivalTime between", value1, value2, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andArrivaltimeNotBetween(Date value1, Date value2) {
			addCriterion("ArrivalTime not between", value1, value2, "arrivaltime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeIsNull() {
			addCriterion("LeavingTime is null");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeIsNotNull() {
			addCriterion("LeavingTime is not null");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeEqualTo(Date value) {
			addCriterion("LeavingTime =", value, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeNotEqualTo(Date value) {
			addCriterion("LeavingTime <>", value, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeGreaterThan(Date value) {
			addCriterion("LeavingTime >", value, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("LeavingTime >=", value, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeLessThan(Date value) {
			addCriterion("LeavingTime <", value, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeLessThanOrEqualTo(Date value) {
			addCriterion("LeavingTime <=", value, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeIn(List<Date> values) {
			addCriterion("LeavingTime in", values, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeNotIn(List<Date> values) {
			addCriterion("LeavingTime not in", values, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeBetween(Date value1, Date value2) {
			addCriterion("LeavingTime between", value1, value2, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andLeavingtimeNotBetween(Date value1, Date value2) {
			addCriterion("LeavingTime not between", value1, value2, "leavingtime");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Boolean value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Boolean value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Boolean value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Boolean value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Boolean value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Boolean> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Boolean> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Boolean value1, Boolean value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table registrationrecord
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table registrationrecord
     *
     * @mbg.generated do_not_delete_during_merge Fri Mar 09 13:47:47 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}