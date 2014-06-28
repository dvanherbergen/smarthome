/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.events;

/**
 * Interface for subscribing to State Events on the SmartHome event bus.
 * 
 * To receive state events, implement this interface and publish it via
 * Declarative Services.
 * 
 * @author Davy Vanherbergen
 * 
 */
public interface StateEventSubscriber {

	/**
	 * Callback method for processing a StateEvent. This method will be called
	 * once for every StateEvent received on the EventBus.
	 * 
	 * @param event StatedEvent
	 */
	public void receiveUpdate(StateEvent event);

}
