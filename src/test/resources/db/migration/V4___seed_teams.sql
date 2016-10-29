INSERT INTO `external_groups` (`id`, `description`, `group_provider`, `identifier`, `name`)
VALUES
	(1, 'test description 1', 'org.example', 'urn:collab:group:example.org:name1', 'name1'),
	(2, 'test description 2', 'org.example', 'urn:collab:group:example.org:name2', 'name2'),
	(3, 'test description 3' , 'org.example', 'urn:collab:group:example.org:name3', 'name3');

INSERT INTO `team_external_groups` (`id`, `grouper_team_id`, `external_groups_id`)
VALUES
	(1, 'nl:surfnet:diensten:test123', 1),
	(2, 'nl:surfnet:diensten:test123', 2),
	(3, 'nl:surfnet:diensten:bazen', 3);
