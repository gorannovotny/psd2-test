package hr.abc.psd2.resources.ws.impl;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import hr.abc.psd2.bean.ICardsBean;
import hr.abc.psd2.resources.ws.ICardResources;

public class CardResources implements ICardResources {

	@Inject
	ICardsBean cardsBean;

	@Override
	public Response getCardAccount(UriInfo queryParam, ContainerRequestContext ctx, HttpHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getCardAccountBalances(String accountId, UriInfo queryParam, HttpHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getCardAccountTransactionList(String accountId, UriInfo queryParam, HttpHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response readCardAccount(String accountId, UriInfo queryParam, HttpHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
