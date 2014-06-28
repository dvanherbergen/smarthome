/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.service;

import org.osgi.framework.BundleContext;

/**
 * Service for submitting jobs to be executed in a shared thread pool. There are
 * 2 thread pools available, a default one for executing jobs immediately and
 * background one for executing fixed interval repeating jobs.
 * 
 * By using a shared thread pool rather than spawning dedicated threads from
 * within bundles, resources can be used more efficiently and it become easier
 * to optimize the total number of threads used for smaller devices.
 * 
 * Any repeating jobs submitted by a bundle will automatically be cancelled if
 * the bundle is stopped.
 * 
 * ThreadPool properties can be defined using the following system properties:
 * 
 * threadPool.min=XX to define the minimum number of threads in the pool.<br/>
 * threadPool.max=XX to define the maximum number of threads in the pool.<br/>
 * threadPool.keepAlive=XX to define the time to keep a thread alive after it
 * completes its' work.
 * 
 * For the background jobs, the following system property is available <br/>
 * 
 * threadPool.background.size=XX to define the number of threads in the pool.<br/>
 * 
 * @author Davy Vanherbergen
 */
public interface ThreadPoolService {

	/**
	 * Submit a job to be run immediately in the shared thread pool.
	 * 
	 * @param runnable
	 *            job to run.
	 */
	public void submit(Runnable runnable);

	/**
	 * Submit a job to be run in the shared thread pool after the given delay
	 * has elapsed.
	 * 
	 * @param context
	 *            BundleContext of the bundle submitting the job. When this
	 *            bundle is unloaded from the runtime, the submitted job(s) will
	 *            be cancelled if it still active.
	 * @param job
	 *            job to run.
	 * @param delay
	 *            delay in milliseconds to wait before starting the job.
	 */
	public void submitDelayed(BundleContext context, Runnable job, long delay);

	/**
	 * Submit a job which should be repeated every x milliseconds.
	 * 
	 * @param context
	 *            BundleContext of the bundle submitting the job. When this
	 *            bundle is unloaded from the runtime, the submitted job(s) will
	 *            be cancelled if it still active.
	 * @param job
	 *            job to run.
	 * @param interval
	 *            the time in ms between job executions
	 */
	public void submitRepeating(BundleContext context, Runnable job, long interval);

	/**
	 * Cancels all running repeating jobs which were submitted using the given
	 * bundlecontext. This method will be called automatically when the bundle
	 * who sumitted a jobs is stopped.
	 * 
	 * @param context
	 *            BundleContext of the bundle which submitted the jobs to
	 *            cancel.
	 */
	public void cancelJobs(BundleContext context);

	/**
	 * Checks whether there are any jobs running which were submitted with the
	 * given bundle context.
	 * 
	 * @param context
	 *            BundleContext of the bundle which submitted the jobs.
	 * @return true if at least one job is there.
	 */
	public boolean containsJobs(BundleContext context);
}
