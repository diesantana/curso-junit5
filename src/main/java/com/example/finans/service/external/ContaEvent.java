package com.example.finans.service.external;

import com.example.finans.domain.Conta;

public interface ContaEvent {
	public enum EventType {CREATED, UPDATED, DELETED}
	
	void dispatch(Conta conta, EventType eventType) throws Exception;
}
