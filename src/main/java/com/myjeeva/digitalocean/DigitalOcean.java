/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.myjeeva.digitalocean;

import java.util.List;

import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Key;
import com.myjeeva.digitalocean.pojo.Regions;
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.Sizes;
import com.myjeeva.digitalocean.pojo.Snapshots;

/**
 * <p>
 * <strong>DigitalOcean API client in Java</strong>
 * </p>
 * 
 * <p>
 * A simple and meaningful wrapper methods for <a href="https://api.digitalocean.com/"
 * title="DigitalOcean's API">DigitalOcean's API</a>. All of the RESTful that you find in
 * DigitalOcean API's will be made available via simple java methods.
 * </p>
 * 
 * <p>
 * <strong>Sample Code:</strong><br/>
 * 
 * <pre>
 * // Create a DigitalOcean client
 * DigitalOcean apiClient = new DigitalOceanClient(clientId, apiKey);
 * 
 * // Let's invoke the appropriate method as per need
 * // Fetching all the available droplets from control panel 
 * List&lt;Droplet> droplets = apiClient.getAvailableDroplets();
 * 
 * // Create a new droplet
 * Droplet newDroplet = new Droplet();
 * newDroplet.setName("api-cliet-test-host");
 * newDroplet.setSizeId(66); // 66 => 512MB plan
 * newDroplet.setRegionId(3); // 3 => San Francisco 1 Data center
 * newDroplet.setImageId(473123); // 473123 => Ubuntu 12.10 x64 Image
 * Droplet droplet = apiClient.createDroplet(newDroplet); 
 * 
 * // Fetch droplet information 
 * Droplet droplet = apiClient.getDropletInfo(dropletId);
 * 
 * // Fetch Available Plans/Sizes supported by DigitalOcean
 * List&lt;DropletSize> sizes = apiClient.getAvailableSizes();
 * 
 * and so on... simple to use and effective!
 * </pre>
 * 
 * </p>
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public interface DigitalOcean {

  // ===========================================
  // Droplets methods
  // ===========================================

  /**
   * Method returns all active droplets that are currently running in your account. All available
   * API information is presented for each droplet.
   * 
   * @param pageNo of request pagination
   * @return {@link Droplets}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   **/
  Droplets getAvailableDroplets(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  Kernels getAvailableKernels(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  Snapshots getAvailableSnapshots(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  Backups getAvailableBackups(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  Actions getAvailableActions(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method returns full information for a specific droplet ID that is passed in the URL.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Droplet}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Droplet getDropletInfo(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * <p>
   * Method allows you to create a new droplet. See the required parameters section below for an
   * explanation of the variables that are needed to create a new droplet.
   * </p>
   * 
   * <p>
   * Create a instance of {@link Droplet} object and populated the droplet object appropriately.
   * </p>
   * <p>
   * Minimum required values are -
   * </p>
   * 
   * <pre>
   * {
   *   "name": "example-droplet-name",
   *   "region": "nyc1",
   *   "size": "512mb",
   *   "image": 3445812
   * }
   * </pre>
   * 
   * @param droplet the id of the droplet
   * @return {@link Droplet}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Droplet createDroplet(Droplet droplet) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method destroys one of your droplets; this is irreversible.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Boolean}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Boolean deleteDroplet(Integer dropletId) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;



  // ==============================================
  // Images manipulation (aka Distribution) methods
  // ==============================================
  /**
   * Method returns all the available images that can be accessed by your client ID. You will have
   * access to all public images by default, and any snapshots or backups that you have created in
   * your own account.
   * 
   * @param pageNo of request pagination
   * @return Images
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Images getAvailableImages(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method retrieves the attributes of an image.
   * 
   * @param imageId the image Id of the droplet/snapshot/backup images
   * @return {@link Image}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Image getImageInfo(Integer imageId) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method retrieves the attributes of an image.
   * 
   * @param slug of the public image
   * @return {@link Image}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Image getImageInfo(String slug) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  Image updateImage(Image image) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;


  // ===========================================
  // Regions (aka Data Centers) methods
  // ===========================================
  /**
   * Method will return all the available regions within the DigitalOcean cloud.
   * 
   * @param pageNo of request pagination
   * @return {@link Regions}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Regions getAvailableRegions(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;


  // ===========================================
  // Sizes (aka Available Droplet Plans) methods
  // ===========================================
  /**
   * Method returns all the available sizes that can be used to create a droplet.
   * 
   * @param pageNo of request pagination
   * @return {@link Sizes}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Sizes getAvailableSizes(Integer pageNo) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;


  // ===========================================
  // Domain manipulation methods
  // ===========================================
  /**
   * Method returns all of your available domains from DNS control panel
   * 
   * @param pageNo of request pagination
   * @return {@link Domains}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Domains getAvailableDomains(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method returns the specified domain attributes and zone file info.
   * 
   * @param domainName the name of the domain
   * @return {@link Domain}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Domain getDomainInfo(String domainName) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method creates a new domain name with an A record for the specified [ip_address].
   * 
   * @param domain object with name and IP address for creation
   * @return {@link Domain}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Domain createDomain(Domain domain) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method deletes the specified domain from DNS control panel
   * 
   * @param domainName the name of the domain
   * @return {@link Boolean}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Boolean deleteDomain(String domainName) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;



  // ===========



  /**
   * <p>
   * Method allows you to create a new droplet. See the required parameters section below for an
   * explanation of the variables that are needed to create a new droplet.
   * </p>
   * 
   * <p>
   * Create a instance of {@link Droplet} object and populate following values
   * </p>
   * <ul>
   * <li>Name Required, String, this is the name of the droplet must be formatted by hostname rules</li>
   * <li>Side Id Required, Numeric, this is the id of the size you would like the droplet created at
   * </li>
   * <li>Image Id Required, Numeric, this is the id of the image you would like the droplet created
   * with</li>
   * <li>Region Id Required, Numeric, this is the id of the region you would like your server in</li>
   * </ul>
   * 
   * @param droplet the id of the droplet
   * @param sshKeyIds Numeric CSV, comma separated list of ssh_key_ids that you would like to be
   *        added to the server
   * @return {@link Droplet}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Droplet createDroplet(Droplet droplet, String sshKeyIds) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;


  /**
   * Method allows you to reboot a droplet. This is the preferred method to use if a server is not
   * responding.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response rebootDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to power cycle a droplet. This will turn off the droplet and then turn it
   * back on.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response powerCyleDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to shutdown a running droplet. The droplet will remain in your account.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response shutdownDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to poweroff a running droplet. The droplet will remain in your account.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response powerOffDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to poweron a powered off droplet.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response powerOnDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method will reset the root password for a droplet. Please be aware that this will reboot the
   * droplet to allow resetting the password.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response resetDropletPassword(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to resize a specific droplet to a different size. This will affect the number
   * of processors and memory allocated to the droplet.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response resizeDroplet(Integer dropletId, Integer sizeId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to take a snapshot of the running droplet, which can later be restored or
   * used to create a new droplet from the same image. Please be aware this may cause a reboot.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response takeDropletSnapshot(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to take a snapshot of the running droplet, which can later be restored or
   * used to create a new droplet from the same image. Please be aware this may cause a reboot.
   * 
   * @param dropletId the id of the droplet
   * @param snapshotName the name the snapshot to be created
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response takeDropletSnapshot(Integer dropletId, String snapshotName)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to restore a droplet with a previous image or snapshot. This will be a mirror
   * copy of the image or snapshot to your droplet. Be sure you have backed up any necessary
   * information prior to restore.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response restoreDroplet(Integer dropletId, Integer imageId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to reinstall a droplet with a default image. This is useful if you want to
   * start again but retain the same IP address for your droplet.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response rebuildDroplet(Integer dropletId, Integer imageId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method enables automatic backups which run in the background daily to backup your droplet's
   * data.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response enableDropletBackups(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method disables automatic backups from running to backup your droplet's data.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response disableDropletBackups(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method renames the droplet to the specified name.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response renameDroplet(Integer dropletId, String name) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;



  /**
   * Method allows you to deletes an image. There is no way to restore a deleted image so be careful
   * and ensure your data is properly backed up.
   * 
   * @param imageId the image Id of the droplet/snapshot/backup images
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response deleteImage(Integer imageId) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method allows you to transfer an image to a specified region.
   * 
   * @param imageId the image Id of the droplet/snapshot/backup images
   * @param regionId the region Id of the digitalocean data centers
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Response transferImage(Integer imageId, Integer regionId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /*
   * SSH Key manipulation methods
   */
  /**
   * Method lists all the available public SSH keys in your account that can be added to a droplet.
   * 
   * @return <code>List&lt;SshKey></code>
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.2
   */
  List<Key> getAvailableSshKeys() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method shows a specific public SSH key in your account that can be added to a droplet.
   * 
   * @param sshKeyId the SSH key Id
   * @return {@link Key}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.2
   */
  Key getSshKeyInfo(Integer sshKeyId) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;

  /**
   * Method allows you to add a new public SSH key to your account
   * 
   * @param sshKeyName the name you want to give this SSH key
   * @param sshPublicKey the actual public SSH key
   * @return {@link Key}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.2
   */
  Key addSshKey(String sshKeyName, String sshPublicKey) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method allows you to modify an existing public SSH key in your account.
   * 
   * @param sshKeyId the SSH key Id, you would like to edit
   * @param newSshPublicKey the new public SSH key
   * @return {@link Key}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.2
   */
  Key editSshKey(Integer sshKeyId, String newSshPublicKey) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method will delete the SSH key from your account.
   * 
   * @param sshKeyId the SSH key Id, you would like to delete
   * @return {@link Response}
   * @throws RequestUnsuccessfulException
   * @throws ResourceNotFoundException
   * @throws DigitalOceanException
   * 
   * @since v1.2
   */
  Response deleteSshKey(Integer sshKeyId) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException;



  /**
   * Method returns all of your current domain records from DNS control panel for given domain.
   * 
   * @param domainId the Id of the domain
   * @return <code>List&lt;DomainRecord></code>
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.1
   */
  List<DomainRecord> getDomainRecords(Integer domainId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method creates a new domain record name with an given domain record values
   * 
   * @param domainRecord the domain record values domain Id, record type, data, name, priority,
   *        port, weight
   * @return {@link DomainRecord}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.1
   */
  DomainRecord createDomainRecord(DomainRecord domainRecord) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method returns the specified domain record.
   * 
   * @param domainId the Id of the domain
   * @param recordId the record Id of the domain
   * @return {@link DomainRecord}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.1
   */
  DomainRecord getDomainRecordInfo(Integer domainId, Integer recordId)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * method edits an existing domain record of the given domain.
   * 
   * @param domainRecord the domain record values domain Id, record type, data, name, priority,
   *        port, weight
   * @return {@link DomainRecord}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.1
   */
  DomainRecord editDomainRecord(DomainRecord domainRecord) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /**
   * Method deletes the specified domain record from domain.
   * 
   * @throws RequestUnsuccessfulException
   * @throws ResourceNotFoundException
   * @throws DigitalOceanException
   * 
   * @since v1.1
   */
  Response deleteDomainRecord(Integer domainId, Integer recordId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

  /*
   * Events status methods
   */
  /**
   * Method is primarily used to collect progress of an event and determined by percentage of
   * completion.
   * 
   * @param eventId this is event id of the event you would like to get more information
   * @return {@link Response}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.3
   */
  Response getEventProgress(Integer eventId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException;

}
