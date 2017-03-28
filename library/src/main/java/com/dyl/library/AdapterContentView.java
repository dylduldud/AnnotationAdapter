package com.dyl.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
	public @interface AdapterContentView {
		/**
		 * findview 的ID
		 * */
		int[] value() default {};
	}