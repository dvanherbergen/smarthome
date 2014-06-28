/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.events;

import org.eclipse.smarthome.core.types.State;

/**
 * A StateEvent is an event containing the new State of a specific SmartHome
 * item. StateEvents are typically sent after the state of an item has been
 * changed. A StateEvent is distributed via the EventBus, which will deliver the
 * event to all subscribers.
 * 
 * Bindings can subscribe to State events by implementing the
 * StateEventSubscriber interface and publishing the interface via Declarative
 * Services.
 * 
 * @author Davy Vanherbergen
 * 
 */
public class StateEvent implements Event {

	private String itemName;

	private State state;

	/**
	 * Create a new State event for an item.
	 * 
	 * @param itemName Name of the item for whose ich the command is destined.
	 * @param command Command
	 */
	public StateEvent(String itemName, State state) {
		this.itemName = itemName;
		this.state = state;
	}

	/**
	 * Get the name of the item whose State changed.
	 * 
	 * @return name of the item
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @return State new State of item.
	 */
	public State getState() {
		return state;
	}

}
