package com.blogspot.dboissin.simplegallery.test;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.blogspot.dboissin.simplegallery.server.model.Comment;
import com.blogspot.dboissin.simplegallery.server.model.Gallery;
import com.blogspot.dboissin.simplegallery.server.model.Photo;
import com.blogspot.dboissin.simplegallery.shared.dto.CommentDTO;
import com.blogspot.dboissin.simplegallery.shared.dto.GalleryDTO;
import com.blogspot.dboissin.simplegallery.shared.dto.PhotoDTO;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class TestSimpleGalleryService extends AbstractTestNGSpringContextTests {

	@Autowired
	private ConversionService converter;
	
	@Test(enabled = false)
	public void testDozer() {
		Gallery g = new Gallery();
		g.setId(42L);
		g.setTitle("My gallery");
		g.setDescription("Une description de la galerie");
		Photo p = new Photo();
		Comment c = new Comment();
		Comment c2 = new Comment();
		c2.setComment("Deuxième test du converter");
		p.setFilename("blip/blop.jpg");
		p.setComments(new HashSet<Comment>());
		p.getComments().add(c);
		p.getComments().add(c2);
		c.setComment("test du converter");
		c.setCommentDate(new Date());
		PhotoDTO pdto = converter.convert(p, PhotoDTO.class);
		assertEquals(pdto.getFilename(), "blip/blop.jpg");
		int i = 0;
		for (CommentDTO cdto: pdto.getComments()) {
			if (i++ == 0) {
				assertEquals(cdto.getComment(), "test du converter");
			} else {
				assertEquals(cdto.getComment(), "Deuxième test du converter");
			}
		}
		GalleryDTO galdto = converter.convert(g, GalleryDTO.class);
		assertEquals(galdto.getId(), new Long(42L), "Error : id galdto.");
		assertEquals(galdto.getDescription(), "Une description de la galerie", "Error : description galdto.");
		assertEquals(galdto.getTitle(), "My gallery", "Error : title galdto.");
	}
}
