package com.jims.nurse.typeHandler;

import java.sql.*;

import com.jims.clinic.vo.ComeDeptVo;


import oracle.jdbc.driver.OracleConnection;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.dbcp.DelegatingConnection;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 * 护理端-患者入科 handler
 */
@MappedJdbcTypes(JdbcType.STRUCT)
public class ComeDeptTypeHandler extends BaseTypeHandler<ComeDeptVo> implements TypeHandler<ComeDeptVo> {


    @Override
    public ComeDeptVo getResult(ResultSet arg0, String arg1) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ComeDeptVo getResult(ResultSet arg0, int arg1) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ComeDeptVo getResult(CallableStatement arg0, int arg1)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ComeDeptVo parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public ComeDeptVo getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public ComeDeptVo getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public ComeDeptVo getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }


    @Override
    public void setParameter(PreparedStatement ps, int i, ComeDeptVo comeDeptVo, JdbcType jdbcType) throws SQLException {
        //获取OracleConnection
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.120:1521:his", "his", "admin");
        Object[] result = new Object[2];
        result[0] = comeDeptVo.getBedRecId();
        result[1]=comeDeptVo.getPatientId();
        result[1]="test";
        Struct s;
  /*      result[2]=comeDeptVo.getVisitId();
        result[3]=comeDeptVo.getBedNo();
        result[4]=comeDeptVo.getBedLabel();
        result[5]=comeDeptVo.getName();
        result[6]=comeDeptVo.getSex();
        result[7]=comeDeptVo.getChargeType();
        result[8]=comeDeptVo.getPrePaymentAmount();
        result[9]=comeDeptVo.getAmount();
        result[10]=comeDeptVo.getAdmissionDateTime();
        result[11]=comeDeptVo.getAdmWardDateTime();
        result[12]=comeDeptVo.getDeptStayed();
        result[13]=comeDeptVo.getDoctorUser();
        result[14]=comeDeptVo.getNursingClass();
        result[15]=comeDeptVo.getSuperDoctorId();
        result[16]=comeDeptVo.getPatientCondition();
        result[17]=comeDeptVo.getBodyHeight();
        result[18]=comeDeptVo.getBodyWeight();
        result[19]=comeDeptVo.getParentDoctorId();
        result[20]=comeDeptVo.getDiagnosis();
        result[21]=comeDeptVo.getOnsetDate();
        result[22]=comeDeptVo.getDeptTransferedTo();
        result[23]=comeDeptVo.getAction();*/
        StructDescriptor structdesc = new StructDescriptor("VO_COMEDEPTE", con);
        STRUCT struct = new STRUCT(structdesc, con, result);

        ps.setObject(i, struct);
    }

}
