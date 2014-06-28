/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.events;

/**
 * SmartHome EventBus. Used for sending and receiving command and states between
 * SmartHome components.
 * 
 * @author Davy Vanherbergen
 * 
 */
public interface EventBus extends EventPublisher {

	/**
	 * Add a new StateEventSubscriber to the EventBus. Called by Declarative
	 * Services when a new StateEventSubscriber is published.
	 * 
	 * @param subscriber StateEventSubscriber.
	 */
	public void addStateEventSubscriber(StateEventSubscriber subscriber);

	/**
	 * Add a new CommandEventSubscriber to the EventBus. Called by Declarative
	 * Services when a new CommandEventSubscriber is published.
	 * 
	 * @param subscriber CommandEventSubscriber.
	 */
	public void addCommandEventSubscriber(CommandEventSubscriber subscriber);

	/**
	 * Remove a StateEventSubscriber from the EventBus. Called by Declarative
	 * Services when a new StateEventSubscriber is unregistered from OSGI.
	 * 
	 * @param subscriber StateEventSubscriber to be removed.
	 */
	public void removeStateEventSubscriber(StateEventSubscriber subscriber);

	/**
	 * Remove a CommandEventSubscriber from the EventBus. Called by Declarative
	 * Services when a new CommandEventSubscriber is unregistered from OSGI.
	 * 
	 * @param subscriber CommandEventSubscriber to be removed.
	 */
	public void removeCommandEventSubscriber(CommandEventSubscriber subscriber);

}
