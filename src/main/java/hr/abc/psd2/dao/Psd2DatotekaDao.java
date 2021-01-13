package hr.abc.psd2.dao;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.pis.Psd2DatotekaDto;
import hr.abc.psd2.model.entity.core.IBDatoteka;
import hr.abc.psd2.model.entity.core.IBKorisnik;
import hr.abc.psd2.model.entity.psd2.Psd2Datoteka;
import hr.abc.psd2.util.Bassx2MinConstants;
import hr.abc.psd2.util.IbanFunctions;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.StringFunctions;

@Dependent
public class Psd2DatotekaDao extends GenericMinDao<Object, Psd2DatotekaDto, Integer> {

    private SimpleDateFormat sdfSql = new SimpleDateFormat("yyyy-MM-dd");

    @Inject
    private EntityManager entityManager;

    /**
     * Mapping from DTO to entity.
     */
    @Override
    protected Psd2Datoteka formEntity(Psd2DatotekaDto dto) {

        Psd2Datoteka entity = null;
        if (dto != null) {
            entity = new Psd2Datoteka();
            entity.setFilenDat(dto.getNazivDatoteke());
            entity.setIbkorDat(dto.getSifraIbKorisnika() != null ? entityManager.getReference(IBKorisnik.class, dto.getSifraIbKorisnika()) : null);
            entity.setVrijeDat(dto.getVrijemeNastankaDatoteke() != null ? dto.getVrijemeNastankaDatoteke() : new Date());
            entity.setCheksumDat(dto.getCheksum());
            entity.setStatuDat(dto.getStatusObrade());
            entity.setInameDat(StringUtils.isNotBlank(dto.getInicijatorPlacanjaNaziv()) ? dto.getInicijatorPlacanjaNaziv() : null);
            entity.setIoridDat(StringUtils.isNotBlank(dto.getInicijatorPlacanjaId()) ? dto.getInicijatorPlacanjaId() : null);
        }
        return entity;
    }

    /**
     * Mapping from entity to DTO.
     *
     * @author Kristijan
     */
    @Override
    protected Psd2DatotekaDto formDTO(Object o) {
        Psd2DatotekaDto result = null;
        if (!(o instanceof Psd2Datoteka)) {
            result = this.formSimpleDTO(o);
        } else {
            Psd2Datoteka dat = (Psd2Datoteka) o;
            result = new Psd2DatotekaDto();
            result.setCheksum(dat.getCheksumDat());
            result.setNazivDatoteke(dat.getFilenDat());
            result.setNazivKorisnika(dat.getIbkorDat() != null ? dat.getIbkorDat().getFizkomKom().getNazivKom() : null);
//			result.setNazivKorisnika(dat.getIbkorDat().getFizkomKom().getNazivKom());
            result.setSifra(dat.getSifraDat());
            result.setSifraIbKorisnika(dat.getIbkorDat() != null ? dat.getIbkorDat().getSifraKom() : null);
            result.setVrijemeNastankaDatoteke(dat.getVrijeDat());
            result.setStatusObrade(dat.getStatuDat());
            result.setInicijatorPlacanjaNaziv(dat.getInameDat());
            result.setInicijatorPlacanjaId(dat.getIoridDat());
        }
        return result;
    }

    @Override
    protected Psd2DatotekaDto formSimpleDTO(Object o) {
        Object[] entity = (Object[]) o;
        Psd2DatotekaDto result = null;
        Object object = null;
        if (entity != null) {
            result = new Psd2DatotekaDto();
            int index = 0;
            // sifra_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setSifra((Integer) object);
            else result.setSifra(null);
            // check_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setCheksum((String) object);
            else result.setCheksum(null);
            // filen_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setNazivDatoteke((String) object);
            else result.setNazivDatoteke(null);
            // vrije_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setVrijemeNastankaDatoteke((Date) object);
            else result.setVrijemeNastankaDatoteke(null);
            // ibkor_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setSifraIbKorisnika((Integer) object);
            else result.setSifraIbKorisnika(null);
            // naziv_kom
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setNazivKorisnika((String) object);
            else result.setNazivKorisnika(null);
            // statu_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setStatusObrade((String) object);
            else result.setStatusObrade(null);
            // iname_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setInicijatorPlacanjaNaziv((String) object);
            else result.setInicijatorPlacanjaNaziv(null);
            //iorid_dat
            object = entity[index++];
            if (!StringFunctions.isBlank(object)) result.setInicijatorPlacanjaId((String) object);
            else result.setInicijatorPlacanjaId(null);
        }
        return result;
    }

    /**
     * Entity class for DAO.
     *
     * @author Kristijan
     */
    @Override
    protected Class<?> entityClass() {
        return IBDatoteka.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return Psd2DatotekaDto.class;
    }

    @Override
    public String getBasicSql(Psd2DatotekaDto filterDto) {
        String sql = "select sifra_dat, check_dat, filen_dat, vrije_dat, ibkor_dat, naziv_kom, statu_dat, iname_dat, iorid_dat " +
                "from psd2_datoteka o " +
                "left join ib_komitent ibk on ibkor_dat = ibk.sifra_kom " +
                "left join komitent kom on ibk.komit_kom = kom.sifra_kom " +
                "where 1 = 1 ";
        if (((Psd2DatotekaDto) filterDto).getDatumOd() != null) {
            sql += "and date(vrije_dat) >= TO_DATE('" + sdfSql.format(((Psd2DatotekaDto) filterDto).getDatumOd()) + "','%Y-%m-%d') ";
        }
        if (((Psd2DatotekaDto) filterDto).getDatumDo() != null) {
            sql += "and date(vrije_dat) <= TO_DATE('" + sdfSql.format(((Psd2DatotekaDto) filterDto).getDatumDo()) + "','%Y-%m-%d') ";
        }
        if (((Psd2DatotekaDto) filterDto).getVrijemeNastankaDatoteke() != null) {
            sql += "and date(vrije_dat) = TO_DATE('" + sdfSql.format(((Psd2DatotekaDto) filterDto).getVrijemeNastankaDatoteke()) + "','%Y-%m-%d') ";
        }
        if (((Psd2DatotekaDto) filterDto).getIban() != null && !((Psd2DatotekaDto) filterDto).getIban().isEmpty()) {
            sql += " and ibk.sifra_kom in (select komit_ibr from ib_korisnik_racun inner join partija on parti_ibr = sifra_par and parti_par = '" + IbanFunctions.resolvePartijaFromIban(((Psd2DatotekaDto) filterDto).getIban()) + "') ";
        }
        if (((Psd2DatotekaDto) filterDto).getSifra() != null) {
            sql += " and sifra_dat = " + ((Psd2DatotekaDto) filterDto).getSifra();
        }
        return sql;

    }

    @Override
    public List<Psd2DatotekaDto> filterList(Psd2DatotekaDto filterDto) {
        List<Psd2DatotekaDto> tmpRes = super.filterList(filterDto);
        List<Psd2DatotekaDto> res = new ArrayList<>();
        Psd2DatotekaDto filter = (Psd2DatotekaDto) filterDto;
        res.addAll(tmpRes);
        return res;
    }


    public void validateBeforeCreate(Psd2DatotekaDto dto) throws AbitRuleException {
        String sql = "select count(*) from ib_datoteka where check_dat is not null and check_dat = '" + dto.getCheksum() + "' ";
        Query query = entityManager.createNativeQuery(sql);
        BigDecimal broj = null;
        try {
            broj = (BigDecimal) query.getSingleResult();
        } catch (Exception ex) {
            broj = null;
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("datotekaCheksumError", Bassx2MinConstants.Ib.MESSAGE_BUNDLE));
        }
        if (broj == null || broj.intValue() > 0) {
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("datotekaCheksumValidation", Bassx2MinConstants.Ib.MESSAGE_BUNDLE));
        }
    }


    public String getCheksum(String data) throws AbitRuleException {
        String cheksum;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(data.getBytes());
            byte[] mdbytes = messageDigest.digest();
            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            cheksum = (sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("datotekaUcitavanjeError", new Object[]{e.getMessage()}, Bassx2MinConstants.Ib.MESSAGE_BUNDLE));
        }
        return cheksum;
    }


}
