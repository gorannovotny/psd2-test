package hr.abc.psd2.dao;

import java.sql.Timestamp;

import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.entity.psd2.Basket;

@Dependent
@Transactional
public class BasketDao extends GenericDao<Object, BasketDto, Integer> {

    private static final long serialVersionUID = 1L;

    private String basicSql = "SELECT sifra_bas, cdate_bas, reqid_bas, psuid_bas, psuip_bas, tppid_bas, tppre_bas, batyp_bas, statu_bas  FROM psd2_basket";


    @Override
    protected Basket formEntity(BasketDto dto) {
        Basket entity = null;
        if (dto != null) {
            entity = new Basket();
            entity.setSifraBas(dto.getSifra());
            entity.setCreationDateBas(Timestamp.valueOf(dto.getCreationDateBas()));
            entity.setReqIdBas(dto.getRequestIdBas());
            entity.setPsuIdBas(dto.getPsuIdBas());
            entity.setPsuIpBas(dto.getPsuIpBas());
            entity.setTppIdBas(dto.getTppIdBas());
            entity.setTppReBas(dto.getTppRedirectUriBas());
            entity.setBasketType(dto.getBasketType());
            entity.setStatusBas(dto.getStatusBas());
        }
        return entity;
    }

    @Override
    protected BasketDto formDTO(Object entity) {
        BasketDto dto = null;

        if (entity != null) {
            if (entity instanceof Basket) {
                Basket entityBasket = (Basket) entity;
                dto = new BasketDto();
                dto.setSifra(entityBasket.getSifraBas());
                dto.setCreationDateBas(((Timestamp) entityBasket.getCreationDateBas()).toLocalDateTime());
                dto.setRequestIdBas(entityBasket.getReqIdBas());
                dto.setPsuIdBas(entityBasket.getPsuIdBas());
                dto.setPsuIpBas(entityBasket.getPsuIpBas());
                dto.setTppIdBas(entityBasket.getTppIdBas());
                dto.setTppRedirectUriBas(entityBasket.getTppReBas());
                dto.setBasketType(entityBasket.getBasketType());
                dto.setStatusBas(entityBasket.getStatusBas());
            } else if (entity instanceof Object[]) {
                dto = new BasketDto();
                Object[] entityArray = (Object[]) entity;
                dto.setSifra(entityArray[0] != null ? (Integer) entityArray[0] : null);
                dto.setCreationDateBas(entityArray[1] != null ? (((Timestamp) entityArray[1]).toLocalDateTime()) : null);
                dto.setRequestIdBas(entityArray[2] != null ? (String) entityArray[2] : null);
                dto.setPsuIdBas(entityArray[3] != null ? (String) entityArray[3] : null);
                dto.setPsuIpBas(entityArray[4] != null ? (String) entityArray[4] : null);
                dto.setTppIdBas(entityArray[5] != null ? (String) entityArray[5] : null);
                dto.setTppRedirectUriBas(entityArray[6] != null ? (String) entityArray[6] : null);
                dto.setBasketType(entityArray[7] != null ? (Integer) entityArray[7] : null);
                dto.setStatusBas(entityArray[8] != null ? (String) entityArray[8] : null);
            }
        }
        return dto;
    }

    public BasketDto getBasketDetails(Integer basketId) {
        BasketDto result = null;
        String sql = basicSql + " WHERE sifra_bas = :sifra_bas";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter("sifra_bas", basketId);
        try {
            result = formDTO(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void changeBasketStatus(BasketDto basketDto, String status) throws AbitRuleException {
        basketDto.setStatusBas(status);
        edit(basketDto);
    }



    @Override
    protected Class<?> entityClass() {
        return Basket.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return BasketDto.class;
    }


}
