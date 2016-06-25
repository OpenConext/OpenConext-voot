INSERT INTO `grouper_groups` (`id`, `parent_stem`, `creator_id`, `create_time`, `modifier_id`, `modify_time`, `last_membership_change`, `last_imm_membership_change`, `alternate_name`, `hibernate_version_number`, `name`, `display_name`, `extension`, `display_extension`, `description`, `context_id`, `type_of_group`)
VALUES
	('1', '1', '1', 1433848233189, '1', 1433848320076, 1433848233973, 1433848233973, NULL, 1, 'nl:surfnet:diensten:burr', 'nl:surfnet:diensten:burr', 'burr', 'burr', 'fffff', '1', 'group'),
	('2', '1', '1', 1433841260294, '1', 1433841260339, 1433844210443, 1433844210444, NULL, 1, 'nl:surfnet:diensten:bazenteam', 'nl:surfnet:diensten:Bazenteam', 'bazenteam', 'Bazenteam', 'Eh', '2', 'group'),
	('3', '1', '1', 1433854567493, '1', 1433854567498, 1448452088279, 1448452088280, NULL, 0, 'nl:surfnet:diensten:managementvo', 'nl:surfnet:diensten:managementvo', 'managementvo', 'managementvo', NULL, '3', 'group'),
	('4', '1', '1', 1444037976997, '1', 1444037977790, 1444037984776, 1444037984777, NULL, 1, 'nl:surfnet:diensten:test123', 'nl:surfnet:diensten:test123', 'test123', 'test123', 'Testteam', '4', 'group'),
	('5', '1', '1', 1450765759825, '1', 1450765759840, 1450765760784, 1450765760785, NULL, 0, 'nl:surfnet:diensten:bassie_&_adriaan', 'nl:surfnet:diensten:bassie & adriaan', 'bassie_&_adriaan', 'bassie & adriaan', NULL, '5', 'group');

INSERT INTO `grouper_memberships` (`id`, `member_id`, `owner_id`, `field_id`, `owner_group_id`, `owner_stem_id`, `owner_attr_def_id`, `via_composite_id`, `enabled`, `enabled_timestamp`, `disabled_timestamp`, `mship_type`, `creator_id`, `create_time`, `hibernate_version_number`, `context_id`)
VALUES
	('1', '1', '1', '1', 1, NULL, '1', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594232, 0, '1'),
	('2', '1', '1', '2', 2, NULL, '2', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594369, 0, '2'),
	('3', '1', '1', '3', 3, '3', NULL, NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594838, 0, '3'),
	('4', '1', '1', '4', 4, NULL, '4', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594916, 0, '4'),
	('5', '1', '1', '5', 5, NULL, '5', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406595006, 0, '5'),
	('6', '1', '1', '6', 1, NULL, '1', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594232, 0, '1'),
	('7', '1', '1', '7', 2, NULL, '2', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594369, 0, '2'),
	('8', '1', '1', '7', 3, '3', NULL, NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594838, 0, '3'),
	('9', '1', '1', '7', 4, NULL, '4', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406594916, 0, '4'),
	('10', '1', '1', '7', 5, NULL, '5', NULL, 'T', NULL, NULL, 'immediate', '1', 1428406595006, 0, '5');

INSERT INTO `grouper_fields` (`id`, `grouptype_uuid`, `is_nullable`, `name`, `read_privilege`, `type`, `write_privilege`, `hibernate_version_number`, `context_id`)
VALUES
	('1', '1', 1, 'members', 'read', 'list', 'update', 0, '1'),
	('2', '1', 1, 'admins', 'admin', 'access', 'admin', 0, '2'),
	('3', '1', 1, 'updaters', 'admin', 'access', 'admin', 0, '3'),
	('4', '1', 1, 'members', 'read', 'list', 'update', 0, '1'),
	('5', '1', 1, 'admins', 'admin', 'access', 'admin', 0, '2'),
	('6', '1', 1, 'updaters', 'admin', 'access', 'admin', 0, '3'),
	('7', '1', 1, 'members', 'read', 'list', 'update', 0, '1');

INSERT INTO `grouper_stems` (`id`, `parent_stem`, `name`, `display_name`, `creator_id`, `create_time`, `modifier_id`, `modify_time`, `display_extension`, `extension`, `description`, `last_membership_change`, `alternate_name`, `hibernate_version_number`, `context_id`)
VALUES
	('1', '1', 'nl:surfnet:diensten', 'nl:surfnet:diensten', '1', 1428407370002, NULL, 0, 'diensten', 'diensten', NULL, NULL, NULL, 0, '1');

INSERT INTO `grouper_members` (`id`, `subject_id`, `subject_source`, `subject_type`, `hibernate_version_number`, `sort_string0`, `sort_string1`, `sort_string2`, `sort_string3`, `sort_string4`, `search_string0`, `search_string1`, `search_string2`, `search_string3`, `search_string4`, `name`, `description`, `context_id`)
VALUES
	('1', 'urn:collab:person:example.com:amin', 'openconext-ldap', 'person', 0, 'John Doe', NULL, NULL, NULL, NULL, ',john doe,', NULL, NULL, NULL, NULL, 'John Doe', 'John Doe', NULL);

