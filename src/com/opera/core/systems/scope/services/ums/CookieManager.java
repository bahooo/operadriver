/*
Copyright 2008-2011 Opera Software ASA

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.opera.core.systems.scope.services.ums;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Cookie;

import com.opera.core.systems.ScopeServices;
import com.opera.core.systems.scope.AbstractService;
import com.opera.core.systems.scope.CookieManagerCommand;
import com.opera.core.systems.scope.protos.CookieMngProtos.CookieList;
import com.opera.core.systems.scope.protos.CookieMngProtos.CookieSettings;
import com.opera.core.systems.scope.protos.CookieMngProtos.GetCookieArg;
import com.opera.core.systems.scope.protos.CookieMngProtos.RemoveCookieArg;
import com.opera.core.systems.scope.protos.UmsProtos.Response;
import com.opera.core.systems.scope.services.ICookieManager;

/**
 * Cookie manager to manage cookies via scope
 * @author Deniz Turkoglu
 *
 */
public class CookieManager extends AbstractService implements ICookieManager {


	//syntax = "scope";
	//
	//service CookieManager
	//{
//	    option (version) = "1.0";
	//
//	    /**
//	     * Retrieve a list of cookies for a given domain.
//	     *
//	     * @param domain Name of domain to look for, e.g. "opera.com"
//	     * @param path   Limit cookie list to specified path or subpath.
//	     */
//	    command GetCookie(GetCookieArg) returns (CookieList) = 1;
	//
//	    /**
//	     * Removes selected cookies or all cookies in a domain.
//	     *
//	     * @param domain Name of domain to remove cookies from, e.g. "opera.com"
//	     * @param path   If specified only removes cookies from specified path or subpath.
//	     * @param name   Name of cookie to remove, if unspecified removes all cookies matching domain/path.
//	     */
//	    command RemoveCookie(RemoveCookieArg) returns (Default) = 2;
	//
//	    /**
//	     * Removes all cookies.
//	     */
//	    command RemoveAllCookies(Default) returns (Default) = 3;
	//
//	    command GetCookieSettings(Default) returns (CookieSettings) = 4;
	//}
	//
	// */

	private int maxCookies;
	private int maxCookiesPerDomain;
	private int maxCookieLength;

	public int getMaxCookies() {
		return maxCookies;
	}

	public int getMaxCookiesPerDomain() {
		return maxCookiesPerDomain;
	}

	public int getMaxCookieLength() {
		return maxCookieLength;
	}

	public CookieManager(ScopeServices services, String version) {
		super(services, version);
		services.setCookieManager(this);
	}

	public void init() {
		CookieSettings settings = getCookieSettings();
		maxCookies = settings.getMaxCookies();
		maxCookiesPerDomain = settings.getMaxCookiesPerDomain();
		maxCookieLength = settings.getMaxCookieLength();
	}

	public Set<Cookie> getCookie(String domain, String path) {
		if(domain == null)
			throw new NullPointerException("Domain can not be null");

		GetCookieArg.Builder arg = GetCookieArg.newBuilder();
		arg.setDomain(domain);

		if(path != null)
			arg.setPath(path);

		Response response = executeCommand(CookieManagerCommand.GET_COOKIE, arg);
		CookieList.Builder builder = CookieList.newBuilder();
		buildPayload(response, builder);
		CookieList list = builder.build();

		List<com.opera.core.systems.scope.protos.CookieMngProtos.Cookie> cookies = list.getCookieListList();
		Set<Cookie> result = new HashSet<Cookie>(cookies.size());

		for (com.opera.core.systems.scope.protos.CookieMngProtos.Cookie cookie : cookies) {
			result.add(new Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(), new Date(cookie.getExpires()), cookie.getIsSecure()));
		}

		return result;

	}

	public void removeCookie(String domain, String path, String name) {
		if(domain == null)
			throw new NullPointerException("Domain can not be null");

		RemoveCookieArg.Builder arg = RemoveCookieArg.newBuilder();
		arg.setDomain(domain);

		if(path != null)
			arg.setPath(path);

		if(name != null)
			arg.setName(name);

		executeCommand(CookieManagerCommand.REMOVE_COOKIE, arg);
	}

	public void removeAllCookies() {
		executeCommand(CookieManagerCommand.REMOVE_ALL_COOKIES, null);
	}

	private CookieSettings getCookieSettings() {
		Response response = executeCommand(CookieManagerCommand.GET_COOKIE_SETTINGS, null);
		CookieSettings.Builder builder = CookieSettings.newBuilder();
		buildPayload(response, builder);
		return builder.build();
	}
}
