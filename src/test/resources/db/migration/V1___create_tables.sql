CREATE TABLE `grouper_memberships` (
  `id` varchar(40) NOT NULL,
  `member_id` varchar(40) NOT NULL,
  `owner_id` varchar(40) NOT NULL,
  `field_id` varchar(40) NOT NULL,
  `owner_group_id` varchar(40) DEFAULT NULL,
  `owner_stem_id` varchar(40) DEFAULT NULL,
  `owner_attr_def_id` varchar(40) DEFAULT NULL,
  `via_composite_id` varchar(40) DEFAULT NULL,
  `enabled` varchar(1) NOT NULL DEFAULT 'T',
  `enabled_timestamp` bigint(20) DEFAULT NULL,
  `disabled_timestamp` bigint(20) DEFAULT NULL,
  `mship_type` varchar(32) NOT NULL,
  `creator_id` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `hibernate_version_number` bigint(20) DEFAULT NULL,
  `context_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `grouper_groups` (
  `id` varchar(40) NOT NULL,
  `parent_stem` varchar(40) NOT NULL,
  `creator_id` varchar(40) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `modifier_id` varchar(40) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `last_membership_change` bigint(20) DEFAULT NULL,
  `last_imm_membership_change` bigint(20) DEFAULT NULL,
  `alternate_name` varchar(1024) DEFAULT NULL,
  `hibernate_version_number` bigint(20) DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `display_name` varchar(1024) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `display_extension` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `context_id` varchar(40) DEFAULT NULL,
  `type_of_group` varchar(10) NOT NULL DEFAULT 'group',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `grouper_fields` (
  `id` varchar(40) NOT NULL,
  `grouptype_uuid` varchar(40) NOT NULL,
  `is_nullable` tinyint(1) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `read_privilege` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  `write_privilege` varchar(32) NOT NULL,
  `hibernate_version_number` bigint(20) DEFAULT NULL,
  `context_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `grouper_members` (
  `id` varchar(40) NOT NULL,
  `subject_id` varchar(255) NOT NULL,
  `subject_source` varchar(255) NOT NULL,
  `subject_type` varchar(255) NOT NULL,
  `hibernate_version_number` bigint(20) DEFAULT NULL,
  `sort_string0` varchar(50) DEFAULT NULL,
  `sort_string1` varchar(50) DEFAULT NULL,
  `sort_string2` varchar(50) DEFAULT NULL,
  `sort_string3` varchar(50) DEFAULT NULL,
  `sort_string4` varchar(50) DEFAULT NULL,
  `search_string0` varchar(2048) DEFAULT NULL,
  `search_string1` varchar(2048) DEFAULT NULL,
  `search_string2` varchar(2048) DEFAULT NULL,
  `search_string3` varchar(2048) DEFAULT NULL,
  `search_string4` varchar(2048) DEFAULT NULL,
  `name` varchar(2048) DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `context_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

