package com.example.project.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
	/*
	 * The CurrentUser annotation is a wrapper around @AuthenticationPrincipal annotation
	 * to access the currently authenticated user in the controllers
	 */
}
