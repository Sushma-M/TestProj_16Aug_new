/*Copyright (c) 2016-2017 vcstest4.com All Rights Reserved.
 This software is the confidential and proprietary information of vcstest4.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcstest4.com*/
package com.testallservicesforvcs.adventureworks2014.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.testallservicesforvcs.adventureworks2014.JobCandidate;
import com.testallservicesforvcs.adventureworks2014.service.JobCandidateService;


/**
 * Controller object for domain model class JobCandidate.
 * @see JobCandidate
 */
@RestController("AdventureWorks2014.JobCandidateController")
@Api(value = "JobCandidateController", description = "Exposes APIs to work with JobCandidate resource.")
@RequestMapping("/AdventureWorks2014/JobCandidate")
public class JobCandidateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCandidateController.class);

    @Autowired
	@Qualifier("AdventureWorks2014.JobCandidateService")
	private JobCandidateService jobCandidateService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new JobCandidate instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobCandidate createJobCandidate(@RequestBody JobCandidate jobCandidate) {
		LOGGER.debug("Create JobCandidate with information: {}" , jobCandidate);

		jobCandidate = jobCandidateService.create(jobCandidate);
		LOGGER.debug("Created JobCandidate with information: {}" , jobCandidate);

	    return jobCandidate;
	}

    @ApiOperation(value = "Returns the JobCandidate instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobCandidate getJobCandidate(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting JobCandidate with id: {}" , id);

        JobCandidate foundJobCandidate = jobCandidateService.getById(id);
        LOGGER.debug("JobCandidate details with id: {}" , foundJobCandidate);

        return foundJobCandidate;
    }

    @ApiOperation(value = "Updates the JobCandidate instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobCandidate editJobCandidate(@PathVariable("id") Integer id, @RequestBody JobCandidate jobCandidate) {
        LOGGER.debug("Editing JobCandidate with id: {}" , jobCandidate.getJobCandidateId());

        jobCandidate.setJobCandidateId(id);
        jobCandidate = jobCandidateService.update(jobCandidate);
        LOGGER.debug("JobCandidate details with id: {}" , jobCandidate);

        return jobCandidate;
    }

    @ApiOperation(value = "Deletes the JobCandidate instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteJobCandidate(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting JobCandidate with id: {}" , id);

        JobCandidate deletedJobCandidate = jobCandidateService.delete(id);

        return deletedJobCandidate != null;
    }

    /**
     * @deprecated Use {@link #findJobCandidates(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of JobCandidate instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<JobCandidate> searchJobCandidatesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering JobCandidates list by query filter:{}", (Object) queryFilters);
        return jobCandidateService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of JobCandidate instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<JobCandidate> findJobCandidates(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering JobCandidates list by filter:", query);
        return jobCandidateService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of JobCandidate instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<JobCandidate> filterJobCandidates(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering JobCandidates list by filter", query);
        return jobCandidateService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportJobCandidates(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return jobCandidateService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportJobCandidatesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = JobCandidate.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> jobCandidateService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of JobCandidate instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countJobCandidates( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting JobCandidates");
		return jobCandidateService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getJobCandidateAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return jobCandidateService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service JobCandidateService instance
	 */
	protected void setJobCandidateService(JobCandidateService service) {
		this.jobCandidateService = service;
	}

}