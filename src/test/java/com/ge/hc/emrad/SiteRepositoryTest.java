package com.ge.hc.emrad;

import com.ge.hc.emrad.configuration.RepositoryTestConfiguration;
import com.ge.hc.emrad.xer.configuration.RepositoryConfiguration;
import com.ge.hc.emrad.xer.domain.Site;
import com.ge.hc.emrad.xer.repository.SiteRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.SortedMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by karstenspakowski on 21/03/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryTestConfiguration.class})
public class SiteRepositoryTest {


    private SiteRepository siteRepository;

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }


    @Test
    @Ignore
    public void testSaveProduct(){
        //setup product
        Site site = new Site();
        site.setName("Union");
        site.setCity("Berlin");


        //save product, verify has ID value after save
        assertNull(site.getId()); //null before save
        siteRepository.save(site);
        assertNotNull(site.getId()); //not null after save

        //fetch from DB
        Site fetchedSite = siteRepository.findOne(site.getId());

        //should not be null
        assertNotNull(fetchedSite);

        //should equal
        assertEquals(site.getId(), fetchedSite.getId());
        assertEquals(site.getName(), fetchedSite.getName());

        //update description and save
        fetchedSite.setName("1.FC Union");
        siteRepository.save(fetchedSite);

        //get from DB, should be updated
        Site fetchedUpdatedSite = siteRepository.findOne(fetchedSite.getId());
        assertEquals(fetchedSite.getName(), fetchedUpdatedSite.getName());

        //verify count of products in DB
        long siteCount = siteRepository.count();
        assertEquals(siteCount, 1);

        //get all products, list should only have one
        Iterable<Site> sites = siteRepository.findAll();

        int count = 0;

        for(Site s : sites){
            count++;
        }

        assertEquals(count, 1);
    }

}
