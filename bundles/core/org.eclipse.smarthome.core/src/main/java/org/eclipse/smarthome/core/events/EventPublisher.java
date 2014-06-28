/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.events;

import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;

/**
 * An EventPublisher is used to send commands or status updates to the SmartHome
 * event bus.
 * 
 * @author Kai Kreuzer - Initial Contribution.
 * @author Davy Vanherbergen - Added new Event methods.
 */
public interface EventPublisher {

	/**
	 * Initiate asynchronous sending of an event. This method returns
	 * immediately to the caller.
	 * 
	 * @param Event the CommandEvent or StateEvent to send.
	 */
	public void postEvent(Event event);

	/**
	 * Initiate synchronous sending of an event. This method does not return to
	 * the caller until all subscribers have processed the command.
	 * 
	 * @param Event the CommandEvent or StateEvent to send.
	 */
	public void sendEvent(Event event);

	/**
	 * Deprecated. Use postEvent(StateEvent event) instead.
	 * 
	 * Initiate asynchronous sending of a status update. This method returns
	 * immediately to the caller.
	 * 
	 * @param itemName name of the item to send the update for
	 * @param newState the new state to send
	 */
	@Deprecated
	public void postUpdate(String itemName, State newState);

	/**
	 * Deprecated. Use postEvent(CommandEvent event) instead.
	 * 
	 * Initiate asynchronous sending of a command. This method returns
	 * immediately to the caller.
	 * 
	 * @param itemName name of the item to send the command for
	 * @param command the command to send
	 */
	@Deprecated
	public void postCommand(String itemName, Command cmd);

	/**
	 * Deprecated. Use sendEvent(CommandEvent event) instead.
	 * 
	 * Initiate synchronous sending of a command. This method does not return to
	 * the caller until all subscribers have processed the command.
	 * 
	 * @param itemName name of the item to send the command for
	 * @param command the command to send
	 */
	@Deprecated
	public void sendCommand(String itemName, Command cmd);

}
