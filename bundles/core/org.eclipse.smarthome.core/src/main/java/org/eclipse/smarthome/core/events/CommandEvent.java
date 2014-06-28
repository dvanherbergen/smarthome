/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.events;

import org.eclipse.smarthome.core.types.Command;

/**
 * A CommandEvent is an event containing a command for a specific SmartHome
 * item. CommandEvents are typically sent from the command line or triggered via
 * rules or the UI. A CommandEvent is distributed via the EventBus, which will
 * deliver the event to all subscribers.
 * 
 * Bindings can subscribe to command events by implementing the
 * CommandEventSubscriber interface and publishing the interface via Declarative
 * Services.
 * 
 * @author Davy Vanherbergen
 * 
 */
public class CommandEvent implements Event {

	private String itemName;

	private Command command;

	/**
	 * Create a new command event for an item.
	 * 
	 * @param itemName Name of the item for which the command is destined.
	 * @param command Command
	 */
	public CommandEvent(String itemName, Command command) {
		this.itemName = itemName;
		this.command = command;
	}

	/**
	 * Get the name of the item for which this command is destined.
	 * 
	 * @return name of the item
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Get the Command contained in the event.
	 * 
	 * @return Command
	 */
	public Command getCommand() {
		return command;
	}

}
