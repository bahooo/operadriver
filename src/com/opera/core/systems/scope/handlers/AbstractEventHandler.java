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
package com.opera.core.systems.scope.handlers;

import com.opera.core.systems.ScopeServices;
import com.opera.core.systems.scope.protos.ConsoleLoggerProtos.ConsoleMessage;
import com.opera.core.systems.scope.protos.EcmascriptProtos.ReadyStateChange;
import com.opera.core.systems.scope.protos.EsdbgProtos.RuntimeInfo;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;
import com.opera.core.systems.scope.protos.DesktopWmProtos.DesktopWindowInfo;


/**
 * Event handler for scope events and network exceptions
 * @author Deniz Turkoglu
 *
 */
public abstract class AbstractEventHandler {


	public AbstractEventHandler(ScopeServices services) {
	}

	/**
	 * Fired when a new runtime started is received
	 * Runtime-started is needed for tracking ecmascript injections
	 * @param started
	 */

	public abstract void onRuntimeStarted(RuntimeInfo started);

	/**
	 * Fired on new console messages
	 * @param message
	 */

	public abstract void onMessage(ConsoleMessage message);

	/**
	 * Fired when a runtime is stopped and no longer injectable
	 * @param id
	 */
	public abstract void onRuntimeStopped(Integer id);

	/**
	 * Fired when a new window is created or window
	 * has incoming changes (such as title change)
	 * @param window
	 */
	public abstract void onUpdatedWindow(WindowInfo window);

	/**
	 * Fired when a window becomes active (steals focus)
	 * @param id
	 */
	public abstract void onActiveWindow(Integer id);

	/**
	 * Fired when a window instance is closed
	 * @param id
	 */
	public abstract void onWindowClosed(Integer id);

	/**
	 * Fired when a window load is complete
	 * @param windowId Id of the window that is loaded
	 */
	public abstract void onWindowLoaded(int windowId);


	/**
	 * Fired when a desktop window is shown at the last possible
	 * moment so the window should be fully visible
	 * @param info
	 */
	public abstract void onDesktopWindowShown(DesktopWindowInfo info);

	/**
	 * Fired when opera is idle
	 */
	public abstract void onOperaIdle();

  /**
   * Fired when a new window is created or window
   * has incoming changes (such as title change)
   * @param info
   */
	public abstract void onDesktopWindowUpdated(DesktopWindowInfo info);

	/**
	 * Fired when a window becomes active (steals focus)
	 * @param info
	 */
	public abstract void onDesktopWindowActivated(DesktopWindowInfo info);

	/**
	 * Fired when a window instance is closed
	 * @param info
	 */
	public abstract void onDesktopWindowClosed(DesktopWindowInfo info);

	/**
	 * Fired when loading Finished event
	 * @param info
	 */
	public abstract void onDesktopWindowLoaded(DesktopWindowInfo info);

	public abstract void onHttpResponse(int responseCode);

	public abstract void onReadyStateChange(ReadyStateChange change);

	public int parseHeader(String header) {
		int responseCode = 0;
		try {
			responseCode = Integer.parseInt(header.split(" ")[1]);
		} catch (NumberFormatException e) {
			//ignore for now
		}
		return responseCode;
	}

	public abstract void onRequest(int windowId);
}

