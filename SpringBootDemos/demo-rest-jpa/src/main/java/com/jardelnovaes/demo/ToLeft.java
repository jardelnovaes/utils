package com.jardelnovaes.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("toLeft")
public class ToLeft implements Actions {
	@Override
	public String getName() {
		return "toLeft";
	}
}