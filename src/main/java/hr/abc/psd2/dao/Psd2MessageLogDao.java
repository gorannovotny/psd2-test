package hr.abc.psd2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.Psd2MessageLogDto;
import hr.abc.psd2.model.entity.psd2.Psd2MessageLog;

import javax.enterprise.context.Dependent;

@Dependent
public class Psd2MessageLogDao extends GenericDao<Object, Psd2MessageLogDto, Integer> {

    @Override
    protected Psd2MessageLog formEntity(Psd2MessageLogDto dto) {
        Psd2MessageLog entity = null;
        if (dto != null) {
            entity = new Psd2MessageLog();
            entity.setIdMsg(dto.getSifra());
            entity.setMtypeMsg(dto.getMessageType());
            entity.setReqidMsg(dto.getRequestId());
            entity.setMessgMsg(dto.getMessage());
            entity.setCdateMsg(dto.getCreationDate());
            entity.setTppidMsg(dto.getTppId());
            entity.setTppadMsg(dto.getTppadMsg());
            entity.setRhostMsg(dto.getRhostMsg());
            entity.setPsuadMsg(dto.getPsuadMsg());
        }
        return entity;
    }

    @Override
    protected Psd2MessageLogDto formDTO(Object entry) {
        Psd2MessageLogDto dto = null;
//        if (entry != null) {
//            dto = new Psd2MessageLogDto();
//            if (entry instanceof Psd2MessageLog) {
//                Psd2MessageLog entity = (Psd2MessageLog) entry;
//                dto.setSifra(entity.getIdMsg());
//                //dto.setMessage(entity.getMessgMsg());
//                dto.setMessageType(entity.getMtypeMsg());
//                dto.setCreationDate(entity.getCdateMsg());
//            } else {
//                Object[] entityArray = (Object[]) entry;
//                dto = new Psd2MessageLogDto();
//                dto.setSifra((Integer) entityArray[0]);
//                dto.setMessage((String) entityArray[1]);
//                dto.setMessageType((String) entityArray[2]);
//                dto.setSifraNalog((Integer) entityArray[3]);
//            }
//        }
        return dto;
    }

    @Override
    public Psd2MessageLogDto create(Psd2MessageLogDto dto) throws AbitRuleException {
        Psd2MessageLogDto psd2MessageLogDto = null;
        String ins = "INSERT INTO psd2_message_log (id_msg, reqid_msg, mtype_msg, cdate_msg, messg_msg, tppid_msg, tppad_msg, rhost_msg, psuad_msg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getDataSource().getConnection()) {
            PreparedStatement psInsert = null;
            psInsert = conn.prepareStatement(ins);
            psInsert.setInt(1, 0);
            psInsert.setString(2, dto.getRequestId());
            psInsert.setString(3, dto.getMessageType());
            psInsert.setObject(4, dto.getCreationDate());
            psInsert.setAsciiStream(5, IOUtils.toInputStream(dto.getMessage()));
            psInsert.setString(6, dto.getTppId());
            psInsert.setString(7, dto.getTppadMsg());
            psInsert.setString(8, dto.getRhostMsg());
            psInsert.setString(9, dto.getPsuadMsg());
            psInsert.executeUpdate();

            psInsert.close();
            getEntityManager().flush();
            return psd2MessageLogDto;
        } catch (SQLException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
            throw new AbitRuleException("Greška kod spremanja psd2 poruke u bazu " + e.getMessage());
        }
    }

    @Override
    public String getBasicSql(Psd2MessageLogDto filterDto) {
        return super.getBasicSql(filterDto);
    }

    @Override
    protected Class<?> entityClass() {
        return null;
    }

    @Override
    protected Class<?> dtoClass() {
        return null;
    }

}
