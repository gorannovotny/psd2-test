//package hr.abc.psd2.web;
//
//import hr.abc.psd2.util.GenericBassxConstants;
//import hr.abc.psd2.util.InternationalizationUtil;
//import hr.abc.psd2.dto.authorization.AuthorizationDto;
//import hr.abc.psd2.dto.authorization.ScaStatus;
//import hr.abc.psd2.dto.pis.TppNalogPlatniDto;
//import hr.abc.psd2.util.Psd2Constants;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// *
// * @author jgabric
// */
//@Named
//@ViewScoped
//public class PotpisNalogSingleController extends PotpisNalogController {
//
//    protected static final long serialVersionUID = 1L;
//
//
//    public PotpisNalogSingleController() {
//        super();
//        setIsPotpis(Boolean.TRUE);
//    }
//
//    @PostConstruct
//    public void init() {
//        super.init();
//
//        //Tu dohvatimo nalog id=broj u autorizaciji
//        if(refTppNaloga != null && !StringUtils.isEmpty(refTppNaloga)){
//            AuthorizationDto authorizationDto = authDao.getAuthorizationByLink(refTppNaloga, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue());
//            if(authorizationDto != null) {
//                if(Arrays.asList(AuthorizationDto.SCA_STATUSI_ZA_POTPIS).contains(authorizationDto.getScastAuth())) {
//                    nepotpisaniNalozi = new ArrayList<>();
//                    TppNalogPlatniDto nalogTpp = nalogDao.getTppNalogBySifra(authorizationDto.getObjectAuth(), true);
//                    if (nalogTpp != null) {
//                        nalogTpp.setAuthorization(authorizationDto);
//                        createDto = nalogTpp;
//                        initNalog();
//                        nepotpisaniNalozi.add(createDto);
//                        if(nalogTpp.getAuthorization().isExpired(LocalDateTime.now())){
//                            showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_authExpired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                        }
//                        redirectLink = redirectLink == null ? nalogTpp.getTppRedirectUri() : redirectLink;
//                    } else {
//                        showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nePostoji", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                    }
//                }else{
//                    showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nePostojiAutorizacija", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                }
//            }
//        }else{
//            showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_idNePostoji",GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//   }
//
//
//}
