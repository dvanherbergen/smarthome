/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.events;

/**
 * Interface for subscribing to CommandEvents on the SmartHome event bus.
 * 
 * To receive command events, implement this interface and publish it via
 * Declarative Services.
 * 
 * @author Davy Vanherbergen
 * 
 */
public interface CommandEventSubscriber {

	/**
	 * Callback method for processing a CommandEvent. This method will be called
	 * once for every CommandEvent received on the EventBus.
	 * 
	 * @param event CommandEvent
	 */
	public void receiveCommand(CommandEvent event);

}
