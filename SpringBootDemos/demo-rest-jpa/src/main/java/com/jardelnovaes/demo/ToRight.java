package com.jardelnovaes.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToRight  implements Actions {
	@Override
	public String getName() {
		return "toRight";
	}
}