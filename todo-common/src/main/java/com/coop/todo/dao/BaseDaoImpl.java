package com.coop.todo.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * @author sankar
 * @param <T> any modal object
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	protected MongoCollection<T> coll = null;
	
	public BaseDaoImpl() {}
	
	public BaseDaoImpl(MongoCollection<T> coll) {
		this.coll = coll;
	}
	
	public T SaveOne(T obj) {
		this.coll.insertOne(obj);
		return obj;
	}

	public List<T> saveMany(List<T> objs) {
		this.coll.insertMany(objs);
		return objs;
	}

	public T updateOne(T obj) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		this.coll.updateOne(Filters.eq("id", obj.getClass().getField("id").get(obj)), new Document("$set",obj));
		return obj;
	}

	public List<T> updateMany(List<T> objs) {
		//this.coll.updateOne(Filters.eq("id", objs.get(0).getClass().getField("id").get(obj.get)), new Document("$set",obj));
		return objs;
	}

	public T findById(Object id) {
		return this.coll.find(Filters.eq("id",id)).first();
	}

	public List<T> findAll() {
		List<T> list = new ArrayList<T>();
		return this.coll.find().into(list);
	}
	
	
	
}
