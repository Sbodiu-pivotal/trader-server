package io.pivotal.apac;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SingleColumnMoneyUserType implements org.hibernate.usertype.UserType {

    public static String MONEY = "Money";

    @Override
    public int[] sqlTypes() {
        return new int[Types.VARCHAR];
    }

    @Override
    public Class returnedClass() {
        return Money.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if ((x == null) && (y == null)) {
            return true;
        }
        if ((x == null) || (y == null)) {
            return false;
        }
        if (x == y) {
            return true;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        final String s = rs.getString(names[0]);
        return Money.of(CurrencyUnit.USD, new BigDecimal(s));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        String s = ((Money) value).getAmount().toString();
        st.setString(index, s);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        final Serializable result;

        if (value == null) {
            result = null;
        } else {
            final Object deepCopy = deepCopy(value);
            if (!(deepCopy instanceof Serializable)) {
                throw new HibernateException(String.format("deepCopy of %s is not serializable", value), null);
            }
            result = (Serializable) deepCopy;
        }

        return result;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

}